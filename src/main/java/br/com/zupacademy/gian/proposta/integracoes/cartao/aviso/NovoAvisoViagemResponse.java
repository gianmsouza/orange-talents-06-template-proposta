package br.com.zupacademy.gian.proposta.integracoes.cartao.aviso;

public class NovoAvisoViagemResponse {
	
	private ResultadoAviso resultado;
	
	public NovoAvisoViagemResponse() {}

	public NovoAvisoViagemResponse(ResultadoAviso resultado) {
		this.resultado = resultado;
	}
	
	public ResultadoAviso getResultado() {
		return resultado;
	}
}
