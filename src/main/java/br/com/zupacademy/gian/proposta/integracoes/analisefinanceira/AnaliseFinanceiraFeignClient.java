package br.com.zupacademy.gian.proposta.integracoes.analisefinanceira;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "analiseFinanceira", url = "${url.analise.financeira}")
public interface AnaliseFinanceiraFeignClient {

	@RequestMapping(method = RequestMethod.POST)
	public AnaliseFinanceiraResponse resultadoSolicitacao(@RequestBody AnaliseFinanceiraRequest request);
}
