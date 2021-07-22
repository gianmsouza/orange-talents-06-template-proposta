package br.com.zupacademy.gian.proposta.integracoes.cartao.bloqueio;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class BloqueioCartao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private LocalDateTime dataBloqueio;

	@NotNull
	private String ip;

	@NotNull
	private String userAgent;
	
	@NotNull
	private String numeroCartao;
	
	@Deprecated
	public BloqueioCartao() {}

	public BloqueioCartao(String ip, String userAgent, String numeroCartao) {
		this.ip = ip;
		this.userAgent = userAgent;
		this.numeroCartao = numeroCartao;
		this.dataBloqueio = LocalDateTime.now();
	}

	public Long getId() {
		return id;
	}

	public LocalDateTime getDataBloqueio() {
		return dataBloqueio;
	}

	public String getIp() {
		return ip;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public String getNumeroCartao() {
		return numeroCartao;
	}
}
