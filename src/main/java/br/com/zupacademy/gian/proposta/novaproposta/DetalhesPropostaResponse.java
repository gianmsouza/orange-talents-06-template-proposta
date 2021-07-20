package br.com.zupacademy.gian.proposta.novaproposta;

import java.math.BigDecimal;

public class DetalhesPropostaResponse {

	private String documento;
	private String email;
	private String endereco;
	private String nome;
	private String numeroCartao;
	private BigDecimal salarioBruto;
	
	public DetalhesPropostaResponse(Proposta proposta) {
		this.documento = proposta.getDocumento();
		this.email = proposta.getEmail();
		this.endereco = proposta.getEndereco();
		this.nome = proposta.getNome();
		this.salarioBruto = proposta.getSalarioBruto();
		
		if (proposta.getNumeroCartao() == null) {
			this.numeroCartao = "Cartão ainda não disponível";
		} else {
			this.numeroCartao = proposta.getNumeroCartao().substring(0, 9) + "-****-****";
		}
	}

	public String getDocumento() {
		return documento;
	}

	public String getEmail() {
		return email;
	}

	public String getEndereco() {
		return endereco;
	}

	public String getNome() {
		return nome;
	}

	public String getNumeroCartao() {
		return numeroCartao;
	}

	public BigDecimal getSalarioBruto() {
		return salarioBruto;
	}
}
