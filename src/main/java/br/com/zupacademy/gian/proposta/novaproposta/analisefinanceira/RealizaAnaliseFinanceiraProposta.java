package br.com.zupacademy.gian.proposta.novaproposta.analisefinanceira;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.zupacademy.gian.proposta.novaproposta.Proposta;
import br.com.zupacademy.gian.proposta.novaproposta.PropostaController;
import feign.FeignException;

@Component
public class RealizaAnaliseFinanceiraProposta {
	
	private final Logger logger = LoggerFactory.getLogger(PropostaController.class);
	
	@Autowired
	private AnaliseFinanceira analiseFinanceira;

	public Proposta realizarAnaliseFinanceira(Proposta proposta) {
		AnaliseFinanceiraRequest analiseFinanceiraReq = new AnaliseFinanceiraRequest(proposta);		
		AnaliseFinanceiraResponse analiseFinanceiraRes = null;
		
		try {
			analiseFinanceiraRes = analiseFinanceira.resultadoSolicitacao(analiseFinanceiraReq);
		} catch (FeignException e) {
			logger.info(e.getMessage());
			analiseFinanceiraRes = new AnaliseFinanceiraResponse(proposta);
		}		
		
		return new Proposta(proposta.getId(), proposta.getDocumento(), proposta.getEmail(), 
				proposta.getNome(), proposta.getEndereco(), proposta.getSalarioBruto(), 
				analiseFinanceiraRes.getResultadoSolicitacao().estadoProposta(proposta));
	}
}
