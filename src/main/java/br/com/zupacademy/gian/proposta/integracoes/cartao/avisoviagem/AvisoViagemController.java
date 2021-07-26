package br.com.zupacademy.gian.proposta.integracoes.cartao.avisoviagem;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.gian.proposta.compartilhado.ErroDeFormularioDto;
import br.com.zupacademy.gian.proposta.novaproposta.Proposta;
import br.com.zupacademy.gian.proposta.novaproposta.PropostaRepository;
import feign.FeignException;

@RestController
@RequestMapping("/cartoes")
public class AvisoViagemController {

	private final Logger logger = LoggerFactory.getLogger(AvisoViagemController.class);

	@Autowired
	private AvisoViagemFeignClient avisoViagemFeign;

	@Autowired
	private AvisoViagemRepository avisoViagemRepository;

	@Autowired
	private PropostaRepository propostaRepository;

	@PostMapping("/{idCartao}/avisos")
	@Transactional
	public ResponseEntity<?> incluirAviso(@PathVariable(required = true) String idCartao,
			@Valid @RequestBody NovoAvisoViagemRequest request, HttpServletRequest httpRequest) {

		Optional<Proposta> proposta = propostaRepository.findByNumeroCartao(idCartao);

		if (!proposta.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ErroDeFormularioDto("cartao", "cartão não encontrado"));
		}

		try {
			String ip = httpRequest.getRemoteAddr();
			String userAgent = httpRequest.getHeader(HttpHeaders.USER_AGENT);

			avisoViagemFeign.enviarAvisoViagem(proposta.get().getNumeroCartao(), 
					new NovoAvisoViagemFeignRequest(request));
					
			 AvisoViagem avisoViagem = request.toModel(
					 proposta.get().getNumeroCartao(),
					 ip, userAgent);
			 
			 avisoViagemRepository.save(avisoViagem);
			 
			 return ResponseEntity.ok().build();
		} catch (FeignException e) {
			
			logger.warn("Erro ao processar aviso de viagem: " + e.getMessage());
			
			if (e.status() == 422) {
				return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
						.body(new ErroDeFormularioDto("aviso", "aviso não pode ser criado, "
								+ "consulte se o mesmo já não foi adicionado anteriormente"));
			}
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
					.body(new ErroDeFormularioDto("aviso", "falha na comunicação com o serviço de avisos"));
		}
	}
}
