package br.com.zupacademy.gian.proposta.integracoes.cartao.bloqueio;

import br.com.zupacademy.gian.proposta.novaproposta.Proposta;

public class NovoBloqueioCartaoResponse {
	
	private Resultado resultado;

	public NovoBloqueioCartaoResponse() {}
	
	public NovoBloqueioCartaoResponse(Resultado resultado) {
		this.resultado = resultado;
	}
	
	public Resultado getResultado() {
		return resultado;
	}

	public Proposta toModel(Proposta proposta) {
		return new Proposta(
				proposta.getId(), 
				proposta.getDocumento(), 
				proposta.getEmail(), 
				proposta.getNome(), 
				proposta.getEndereco(), 
				proposta.getSalarioBruto(),
				Resultado.BLOQUEADO.toString(),
				proposta.getNumeroCartao());
	}
}
