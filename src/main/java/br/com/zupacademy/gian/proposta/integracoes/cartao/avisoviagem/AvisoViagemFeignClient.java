package br.com.zupacademy.gian.proposta.integracoes.cartao.avisoviagem;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "avisoViagem", url = "${url.api.cartao}")
public interface AvisoViagemFeignClient {

	@PostMapping("/{id}/avisos")
	public NovoAvisoViagemResponse enviarAvisoViagem(@PathVariable String id,
			@RequestBody NovoAvisoViagemFeignRequest request);
}