package br.com.zupacademy.gian.proposta.integracoes.cartao.carteiras;

public class SolicitacaoInclusaoCarteiraResponse {

	private ResultadoCarteira resultado;
	private String id;
	
	public SolicitacaoInclusaoCarteiraResponse(ResultadoCarteira resultado, String id) {
		this.resultado = resultado;
		this.id = id;
	}

	public ResultadoCarteira getResultado() {
		return resultado;
	}

	public String getId() {
		return id;
	}
}
