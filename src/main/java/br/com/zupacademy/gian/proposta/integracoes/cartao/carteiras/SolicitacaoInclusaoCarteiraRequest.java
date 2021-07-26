package br.com.zupacademy.gian.proposta.integracoes.cartao.carteiras;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class SolicitacaoInclusaoCarteiraRequest {
	
	@NotBlank
	@Email
	private String email;
	
	@NotNull
	private CarteiraDigital carteira;

	public SolicitacaoInclusaoCarteiraRequest(String email, CarteiraDigital carteira) {
		this.email = email;
		this.carteira = carteira;
	}

	public String getEmail() {
		return email;
	}
	
	public CarteiraDigital getCarteira() {
		return carteira;
	}
}
