package br.com.zupacademy.gian.proposta.integracoes.cartao.aviso;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class AvisoViagem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String destino;
	
	@NotNull
	private LocalDate validoAte;
	
	@NotNull
	private LocalDateTime criadoEm = LocalDateTime.now();
	
	@NotNull
	private String ip;
	
	@NotNull
	private String userAgent;
	
	@NotNull
	private String numeroCartao;
	
	@Deprecated
	public AvisoViagem() {}

	public AvisoViagem(String destino, LocalDate dataTerminoViagem, String ip, String userAgent,
			String numeroCartao) {
		this.destino = destino;
		this.validoAte = dataTerminoViagem;
		this.ip = ip;
		this.userAgent = userAgent;
		this.numeroCartao = numeroCartao;
	}
}
