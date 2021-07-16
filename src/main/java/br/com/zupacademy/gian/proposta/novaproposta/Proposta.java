package br.com.zupacademy.gian.proposta.novaproposta;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Proposta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String documento;

	@Column(nullable = false)
	private String email;

	@Column(nullable = false)
	private String nome;

	@Column(nullable = false)
	private String endereco;

	@Column(nullable = false)
	private BigDecimal salarioBruto;

	@Deprecated
	public Proposta() {}

	public Proposta(String documento, String email, String nome, String endereco, BigDecimal salarioBruto) {
		this.documento = documento;
		this.email = email;
		this.nome = nome;
		this.endereco = endereco;
		this.salarioBruto = salarioBruto;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}
}