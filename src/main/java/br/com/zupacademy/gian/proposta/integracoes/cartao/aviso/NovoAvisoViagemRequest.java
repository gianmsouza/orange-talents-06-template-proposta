package br.com.zupacademy.gian.proposta.integracoes.cartao.aviso;

import java.time.LocalDate;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

public class NovoAvisoViagemRequest {

	@NotBlank
	private String destino;
	
	@NotNull
	@FutureOrPresent
	@JsonFormat(pattern = "yyyy/MM/dd", shape = Shape.STRING)
	private LocalDate validoAte;

	public NovoAvisoViagemRequest() {}
	
	public NovoAvisoViagemRequest(String destino, LocalDate validoAte) {
		this.destino = destino;
		this.validoAte = validoAte;
	}	
	
	public String getDestino() {
		return destino;
	}

	public LocalDate getValidoAte() {
		return validoAte;
	}

	public AvisoViagem toModel(String numeroCartao, String ip, String userAgent) {
		return new AvisoViagem(this.destino, this.validoAte, ip, userAgent, numeroCartao);
	}
}
