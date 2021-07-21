package br.com.zupacademy.gian.proposta.integracoes.cartao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "novoCartao", url = "${url.buscar.numero.cartao}")
public interface NovoCartao {

	@RequestMapping(method = RequestMethod.POST)
	public NovoCartaoResponse buscarNumeroCartao(@RequestBody NovoCartaoRequest request);
}