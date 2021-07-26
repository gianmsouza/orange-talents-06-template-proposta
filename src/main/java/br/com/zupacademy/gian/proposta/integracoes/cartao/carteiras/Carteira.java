package br.com.zupacademy.gian.proposta.integracoes.cartao.carteiras;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Carteira {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String numeroCartao;

	@NotNull
	@Enumerated(EnumType.STRING)
	private CarteiraDigital carteira;

	@Deprecated
	public Carteira() {
	}

	public Carteira(String numeroCartao, CarteiraDigital carteira) {
		this.numeroCartao = numeroCartao;
		this.carteira = carteira;
	}
	
	public Long getId() {
		return id;
	}
}
