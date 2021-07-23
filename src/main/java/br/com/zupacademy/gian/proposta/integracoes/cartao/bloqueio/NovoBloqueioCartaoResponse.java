package br.com.zupacademy.gian.proposta.integracoes.cartao.bloqueio;

import br.com.zupacademy.gian.proposta.novaproposta.Proposta;

public class NovoBloqueioCartaoResponse {
	
	private ResultadoBloqueio resultado;

	public NovoBloqueioCartaoResponse() {}
	
	public NovoBloqueioCartaoResponse(ResultadoBloqueio resultado) {
		this.resultado = resultado;
	}
	
	public ResultadoBloqueio getResultado() {
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
				ResultadoBloqueio.BLOQUEADO.toString(),
				proposta.getNumeroCartao());
	}
}
