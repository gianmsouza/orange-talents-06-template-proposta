package br.com.zupacademy.gian.proposta.biometria;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.gian.proposta.novaproposta.Proposta;

@Entity
public class Biometria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private LocalDateTime dataCriacao;
	
	@ManyToOne
	private Proposta proposta;
	
	@NotNull
	private String biometria;
	
	@Deprecated
	public Biometria() {}	

	public Biometria(Proposta proposta, String biometria) {
		this.dataCriacao = LocalDateTime.now();
		this.proposta = proposta;
		this.biometria = biometria;
	}

	public Long getId() {
		return id;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public Proposta getProposta() {
		return proposta;
	}

	public String getBiometria() {
		return biometria;
	}
}
