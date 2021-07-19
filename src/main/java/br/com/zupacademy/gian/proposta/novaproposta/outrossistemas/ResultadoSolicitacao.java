package br.com.zupacademy.gian.proposta.novaproposta.outrossistemas;

import br.com.zupacademy.gian.proposta.novaproposta.Proposta;

public enum ResultadoSolicitacao {
	COM_RESTRICAO {
		@Override
		public String estadoProposta(Proposta proposta) {
			return "NAO_ELEGIVEL";
		}
	}, 
	SEM_RESTRICAO {
		@Override
		public String estadoProposta(Proposta proposta) {
			return "ELEGIVEL";
		}
	};
	
	public abstract String estadoProposta(Proposta proposta); 
}
