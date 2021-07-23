package br.com.zupacademy.gian.proposta.integracoes.cartao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "novoCartao", url = "${url.api.cartao}")
public interface NovoCartaoFeignClient {
	
	@PostMapping
	public NovoCartaoResponse buscarNumeroCartao(@RequestBody NovoCartaoRequest request);
}
