package br.com.zupacademy.gian.proposta.novaproposta.cartao;

import br.com.zupacademy.gian.proposta.novaproposta.Proposta;

public class NovoCartaoResponse {
	
	private String id;
	
	public Proposta toModel(Proposta proposta) {
		return new Proposta(proposta.getId(), 
				proposta.getDocumento(), 
				proposta.getEmail(), 
				proposta.getNome(), 
				proposta.getEndereco(), 
				proposta.getSalarioBruto(), 
				proposta.getEstadoProposta(),
				this.id);
	}	

	public String getId() {
		return id;
	}
}
