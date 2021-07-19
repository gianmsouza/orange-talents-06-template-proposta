package br.com.zupacademy.gian.proposta.novaproposta.outrossistemas;

import br.com.zupacademy.gian.proposta.novaproposta.Proposta;

public class AnaliseFinanceiraRequest {

	private String documento;
	private String nome;
	private String idProposta;

	public AnaliseFinanceiraRequest(Proposta proposta) {
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
