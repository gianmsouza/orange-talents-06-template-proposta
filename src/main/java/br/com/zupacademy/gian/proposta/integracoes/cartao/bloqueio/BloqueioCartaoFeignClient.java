package br.com.zupacademy.gian.proposta.integracoes.cartao.bloqueio;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "bloqueioCartao", url = "${url.api.cartao}")
public interface BloqueioCartaoFeignClient {

	@PostMapping("/{id}/bloqueios")
	public NovoBloqueioCartaoResponse bloquearCartao(@PathVariable String id,
			@RequestBody NovoBloqueioCartaoRequest request);
}