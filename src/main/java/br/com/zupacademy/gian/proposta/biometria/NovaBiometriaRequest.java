package br.com.zupacademy.gian.proposta.biometria;

import javax.validation.constraints.NotBlank;

import br.com.zupacademy.gian.proposta.compartilhado.FormatoBase64;
import br.com.zupacademy.gian.proposta.novaproposta.Proposta;

public class NovaBiometriaRequest {

	@NotBlank 
	@FormatoBase64(message = "Biometria precisa estar no formato Base64")
	private String biometria;
	
	public NovaBiometriaRequest() {}
	
	public NovaBiometriaRequest(String biometria) {
		this.biometria = biometria;
	}
	
	public Biometria toModel(Proposta proposta) {
		return new Biometria(proposta, this.biometria);
	}	

	public String getBiometria() {
		return biometria;
	}	
}
