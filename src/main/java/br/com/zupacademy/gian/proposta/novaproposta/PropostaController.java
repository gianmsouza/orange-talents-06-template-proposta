package br.com.zupacademy.gian.proposta.novaproposta;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.gian.proposta.compartilhado.ErroDeFormularioDto;
import br.com.zupacademy.gian.proposta.integracoes.analisefinanceira.RealizaAnaliseFinanceiraProposta;
import br.com.zupacademy.gian.proposta.seguranca.CriptografaDocumento;
import io.opentracing.Span;
import io.opentracing.Tracer;

@RestController
@RequestMapping("/propostas")
public class PropostaController {

	@Autowired
	private Tracer tracer;

	@Autowired
	private PropostaRepository propostaRepository;

	@Autowired
	private RealizaAnaliseFinanceiraProposta analiseFinanceira;

	@PostMapping
	@Transactional
	public ResponseEntity<?> salvarNovaProposta(@RequestBody @Valid NovaPropostaRequest request,
			UriComponentsBuilder uriBuilder) {

		List<Proposta> findByDocumento = propostaRepository
				.findByDocumento(CriptografaDocumento.encrypt(request.getDocumento()));

		if (!findByDocumento.isEmpty()) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
					.body(new ErroDeFormularioDto("documento", "documento já cadastrado"));
		}

		Proposta proposta = request.toModel();

		Span activeSpan = tracer.activeSpan();
		activeSpan.setTag("proposta.email", proposta.getEmail());

		propostaRepository.save(proposta);

		proposta = analiseFinanceira.realizarAnaliseFinanceira(proposta);
		propostaRepository.save(proposta);

		URI uri = uriBuilder.path("/proposta/{id}").buildAndExpand(proposta.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<DetalhesPropostaResponse> buscarDadosProposta(@PathVariable Long id) {
		Optional<Proposta> proposta = propostaRepository.findById(id);

		if (proposta.isPresent()) {
			return ResponseEntity.ok(new DetalhesPropostaResponse(proposta.get()));
		}

		return ResponseEntity.notFound().build();
	}
}
