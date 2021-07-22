package br.com.zupacademy.gian.proposta.integracoes.cartao.bloqueio;

public class NovoBloqueioCartaoResponse {
	
	private Resultado resultado;

	public NovoBloqueioCartaoResponse() {}
	
	public NovoBloqueioCartaoResponse(Resultado resultado) {
		this.resultado = resultado;
	}
	
	public Resultado getResultado() {
		return resultado;
	}
}
