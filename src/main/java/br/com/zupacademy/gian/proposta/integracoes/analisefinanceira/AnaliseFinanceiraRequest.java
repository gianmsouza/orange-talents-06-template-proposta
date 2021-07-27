package br.com.zupacademy.gian.proposta.integracoes.analisefinanceira;

import br.com.zupacademy.gian.proposta.novaproposta.Proposta;
import br.com.zupacademy.gian.proposta.seguranca.CriptografaDocumento;

public class AnaliseFinanceiraRequest {

	private String documento;
	private String nome;
	private String idProposta;

	public AnaliseFinanceiraRequest(Proposta proposta) {
		this.documento = CriptografaDocumento.decrypt(proposta.getDocumento());
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
