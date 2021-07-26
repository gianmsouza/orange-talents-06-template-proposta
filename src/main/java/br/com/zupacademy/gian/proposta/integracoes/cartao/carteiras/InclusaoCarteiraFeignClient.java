package br.com.zupacademy.gian.proposta.integracoes.cartao.carteiras;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "inclusaoCartaoCarteiraDigital", url = "${url.api.cartao}")
public interface InclusaoCarteiraFeignClient {

	@PostMapping("/{id}/carteiras")
	public SolicitacaoInclusaoCarteiraResponse solicitarInclusaoCarteira(@PathVariable String id,
			SolicitacaoInclusaoCarteiraRequest request);
}
