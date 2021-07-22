package br.com.zupacademy.gian.proposta.integracoes.cartao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.zupacademy.gian.proposta.novaproposta.Proposta;
import br.com.zupacademy.gian.proposta.novaproposta.PropostaRepository;
import feign.FeignException;

@Component
@EnableScheduling
public class AssociaCartaoNaPropostaAgendamento {
	
	private final Logger logger = LoggerFactory.getLogger(AssociaCartaoNaPropostaAgendamento.class);
	
	@Autowired
	private NovoCartaoFeignClient novoCartao;
	
	@Autowired
	private PropostaRepository propostaRepository;
	 
	@Scheduled(fixedDelayString = "${associar.cartao.proposta.agendamento}")
	public void associarCartaoNaProposta() {
	
		List<Proposta> propostas = propostaRepository.findByPropostaElegivelECartaoNull();
		
		logger.info("Cartões à associar: " + propostas.size());
		
		for (Proposta proposta : propostas) {			
			NovoCartaoRequest request = new NovoCartaoRequest(proposta);			
			
			try {
				NovoCartaoResponse numeroCartao = novoCartao.buscarNumeroCartao(request);
				
				Proposta propostaAtualizada = numeroCartao.toModel(proposta);
				propostaRepository.save(propostaAtualizada);
			} catch (FeignException e) {
				logger.warn(e.getMessage());
			}			
		}		
	}
}
