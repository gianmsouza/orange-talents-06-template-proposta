package br.com.zupacademy.gian.proposta.novaproposta;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.gian.proposta.compartilhado.ErroDeFormularioDto;
import br.com.zupacademy.gian.proposta.novaproposta.analisefinanceira.RealizaAnaliseFinanceiraProposta;

@RestController
@RequestMapping("/proposta")
public class PropostaController {
			
	@Autowired
	private PropostaRepository propostaRepository;
	
	@Autowired
	private RealizaAnaliseFinanceiraProposta analiseFinanceira;
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> salvarNovaProposta(@RequestBody @Valid NovaPropostaRequest request,
			UriComponentsBuilder uriBuilder) {
		
		Proposta proposta = request.toModel();
		
		List<Proposta> findByDocumento = propostaRepository.findByDocumento(proposta.getDocumento());
		
		if (!findByDocumento.isEmpty()) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
					.body(new ErroDeFormularioDto("documento", "documento já cadastrado"));
		}
		
		propostaRepository.save(proposta);
	
		proposta = analiseFinanceira.realizarAnaliseFinanceira(proposta);
		propostaRepository.save(proposta);
		
		URI uri = uriBuilder.path("/proposta/{id}").buildAndExpand(proposta.getId()).toUri();
        return ResponseEntity.created(uri).build();
	}
}
