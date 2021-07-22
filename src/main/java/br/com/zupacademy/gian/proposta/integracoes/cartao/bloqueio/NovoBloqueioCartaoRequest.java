package br.com.zupacademy.gian.proposta.integracoes.cartao.bloqueio;

public class NovoBloqueioCartaoRequest {
	
	private String sistemaResponsavel = "propostas";
	
	public NovoBloqueioCartaoRequest() {}

	public NovoBloqueioCartaoRequest(String sistemaResponsavel) {
		this.sistemaResponsavel = sistemaResponsavel;
	}
	
	public String getSistemaResponsavel() {
		return sistemaResponsavel;
	}
}
