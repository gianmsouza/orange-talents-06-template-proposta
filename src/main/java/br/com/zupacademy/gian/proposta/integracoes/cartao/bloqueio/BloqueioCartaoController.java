package br.com.zupacademy.gian.proposta.integracoes.cartao.bloqueio;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.gian.proposta.compartilhado.ErroDeFormularioDto;
import br.com.zupacademy.gian.proposta.novaproposta.Proposta;
import br.com.zupacademy.gian.proposta.novaproposta.PropostaRepository;
import feign.FeignException;

@RestController
@RequestMapping("/cartoes")
public class BloqueioCartaoController {

	@Autowired
	private PropostaRepository propostaRepository;

	@Autowired
	private BloqueioRepository bloqueioRepository;

	@Autowired
	private BloqueioCartaoFeignClient bloqueioCartao;

	@PostMapping("/{idCartao}/bloqueios")
	@Transactional
	public ResponseEntity<?> bloquearCartao(@PathVariable (required = true) String idCartao, 
			HttpServletRequest request) {

		Optional<Proposta> propostaOptional = propostaRepository.findByNumeroCartao(idCartao);

		if (!propostaOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ErroDeFormularioDto("cartao", "cartão não encontrado"));
		}

		String ip = request.getRemoteAddr();
		String userAgent = request.getHeader(HttpHeaders.USER_AGENT);

		try {
			Proposta proposta = propostaOptional.get();

			NovoBloqueioCartaoResponse bloquearCartaoRes = bloqueioCartao.bloquearCartao(idCartao,
					new NovoBloqueioCartaoRequest());
			BloqueioCartao bloqueio = new BloqueioCartao(ip, userAgent, proposta.getNumeroCartao());
			bloqueioRepository.save(bloqueio);
			
			proposta = bloquearCartaoRes.toModel(proposta);
			propostaRepository.save(proposta);
			
			return ResponseEntity.ok().build();
		} catch (FeignException e) {
			if (e.status() == 422) {
				return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
						.body(new ErroDeFormularioDto("cartao", "cartão já encontra-se bloqueado"));
			}
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
					.body(new ErroDeFormularioDto("cartao", "falha na comunicação com o serviço de bloqueio"));
		}
	}
}
