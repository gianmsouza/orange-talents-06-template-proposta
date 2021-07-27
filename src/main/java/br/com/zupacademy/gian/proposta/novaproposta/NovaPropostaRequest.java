package br.com.zupacademy.gian.proposta.novaproposta;

import java.math.BigDecimal;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import br.com.zupacademy.gian.proposta.compartilhado.CpfOuCnpj;
import br.com.zupacademy.gian.proposta.seguranca.CriptografaDocumento;

public class NovaPropostaRequest {

	@NotBlank
	@CpfOuCnpj
	private String documento;

	@Email
	@NotBlank
	private String email;

	@NotBlank
	private String nome;

	@NotBlank
	private String endereco;

	@NotNull
	@PositiveOrZero
	private BigDecimal salario;

	public NovaPropostaRequest(String documento, String email, String nome, String endereco, BigDecimal salario) {
		this.documento = documento;
		this.email = email;
		this.nome = nome;
		this.endereco = endereco;
		this.salario = salario;
	}

	public Proposta toModel() {
		String docEncriptografado = CriptografaDocumento.encrypt(this.documento);
		return new Proposta(docEncriptografado, this.email, this.nome, this.endereco, this.salario);
	}

	public String getDocumento() {
		return documento;
	}

	public String getEmail() {
		return email;
	}

	public String getNome() {
		return nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public BigDecimal getSalario() {
		return salario;
	}
}
