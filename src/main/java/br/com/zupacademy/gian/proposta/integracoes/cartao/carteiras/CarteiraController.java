package br.com.zupacademy.gian.proposta.integracoes.cartao.carteiras;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import br.com.zupacademy.gian.proposta.integracoes.cartao.avisoviagem.AvisoViagemController;
import br.com.zupacademy.gian.proposta.novaproposta.Proposta;
import br.com.zupacademy.gian.proposta.novaproposta.PropostaRepository;
import feign.FeignException;

@RestController
@RequestMapping("/cartoes")
public class CarteiraController {
	
	private final Logger logger = LoggerFactory.getLogger(AvisoViagemController.class);

	@Autowired
	private InclusaoCarteiraFeignClient inclusaoCarteira;

	@Autowired
	private PropostaRepository propostaRepository;

	@Autowired
	private CarteiraRepository carteiraRepository;

	@PostMapping("/{idCartao}/carteiras")
	@Transactional
	public ResponseEntity<?> incluirCartaoCarteiraDigital(@PathVariable(required = true) String idCartao,
			@RequestBody @Valid SolicitacaoInclusaoCarteiraRequest request,
			UriComponentsBuilder uriBuilder) {

		Optional<Proposta> propostaOptional = propostaRepository.findByNumeroCartao(idCartao);

		if (!propostaOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ErroDeFormularioDto("cartao", "cartão não encontrado"));
		}

		Proposta proposta = propostaOptional.get();

		try {
			inclusaoCarteira.solicitarInclusaoCarteira(proposta.getNumeroCartao(),
					new SolicitacaoInclusaoCarteiraRequest(proposta.getEmail(), request.getCarteira()));
			
			Carteira carteira = new Carteira(proposta.getNumeroCartao(), request.getCarteira());
			carteiraRepository.save(carteira);
			
			URI uri = uriBuilder.path("/cartoes/{id}/carteiras").buildAndExpand(carteira.getId()).toUri();
	        return ResponseEntity.created(uri).build();
	        
		} catch (FeignException e) {
			logger.warn("Erro ao associar cartão à carteira: " + e.getMessage());
			
			if (e.status() == 422) {
				return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
						.body(new ErroDeFormularioDto("carteira", "cartão já atrelado à carteira"));
			}
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
					.body(new ErroDeFormularioDto("carteira", "falha na comunicação com o serviço de carteiras"));
		}
	}
}
