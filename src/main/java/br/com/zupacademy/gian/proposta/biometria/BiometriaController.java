package br.com.zupacademy.gian.proposta.biometria;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.gian.proposta.compartilhado.ErroDeFormularioDto;
import br.com.zupacademy.gian.proposta.novaproposta.Proposta;
import br.com.zupacademy.gian.proposta.novaproposta.PropostaRepository;

@RestController
@RequestMapping("/biometria")
public class BiometriaController {
	
	@Autowired
	private PropostaRepository propostaRepository;
	
	@Autowired
	private BiometriaRepository biometriaRepository;
	
	@PostMapping("/{idCartao}")
	@Transactional
	public ResponseEntity<?> salvarBiometria(@PathVariable String idCartao, 
			@Valid @RequestBody NovaBiometriaRequest request, 
			UriComponentsBuilder uriBuilder) {
		
		Optional<Proposta> proposta = propostaRepository.findByNumeroCartao(idCartao);
		
		if (!proposta.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ErroDeFormularioDto("cartao", "cartão não encontrado"));
		}
		
		Biometria biometria = request.toModel(proposta.get());		
		biometriaRepository.save(biometria);
		
		URI uri = uriBuilder.path("/biometria/{id}").buildAndExpand(biometria.getId()).toUri();
        return ResponseEntity.created(uri).build();
	}
}
