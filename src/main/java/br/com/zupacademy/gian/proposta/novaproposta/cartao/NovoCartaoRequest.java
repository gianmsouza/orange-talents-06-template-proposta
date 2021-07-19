package br.com.zupacademy.gian.proposta.novaproposta.cartao;

import br.com.zupacademy.gian.proposta.novaproposta.Proposta;

public class NovoCartaoRequest {

	private String documento;
	private String nome;
	private String idProposta;

	public NovoCartaoRequest(Proposta proposta) {
		this.documento = proposta.getDocumento();
		this.nome = proposta.getNome();
		this.idProposta = proposta.getId().toString();
	}

	public String getDocumento() {
		return documento;
	}

	public String getNome() {
		return nome;
	}

	public String getIdProposta() {
		return idProposta;
	}
}
