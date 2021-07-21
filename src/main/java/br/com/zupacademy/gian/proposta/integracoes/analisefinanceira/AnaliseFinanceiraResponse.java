package br.com.zupacademy.gian.proposta.integracoes.analisefinanceira;

import br.com.zupacademy.gian.proposta.novaproposta.Proposta;

public class AnaliseFinanceiraResponse {

	private String documento;
	private String nome;
	private String idProposta;
	private ResultadoSolicitacao resultadoSolicitacao;
	
	public AnaliseFinanceiraResponse() {}

	public AnaliseFinanceiraResponse(Proposta proposta) {
		this.documento = proposta.getDocumento();
		this.nome = proposta.getNome();
		this.idProposta = proposta.getId().toString();
		this.resultadoSolicitacao = ResultadoSolicitacao.COM_RESTRICAO;
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

	public ResultadoSolicitacao getResultadoSolicitacao() {
		return resultadoSolicitacao;
	}
}
