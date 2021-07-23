package br.com.zupacademy.gian.proposta.integracoes.cartao.aviso;

public class NovoAvisoViagemFeignRequest {

	private String destino;
	private String validoAte;	

	public NovoAvisoViagemFeignRequest(NovoAvisoViagemRequest request) {
		this.destino = request.getDestino();
		this.validoAte = request.getValidoAte().toString();		
	}
	
	public String getDestino() {
		return destino;
	}
	
	public String getValidoAte() {
		return validoAte;
	}
}
