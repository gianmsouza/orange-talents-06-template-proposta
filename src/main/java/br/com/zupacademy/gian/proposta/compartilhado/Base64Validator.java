package br.com.zupacademy.gian.proposta.compartilhado;

import java.util.Base64;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class Base64Validator implements ConstraintValidator<FormatoBase64, String> {

	@Override
	public boolean isValid(String base64, ConstraintValidatorContext constraint) {
		try {
			Base64.getDecoder().decode(base64);
			return true;
		} catch (IllegalArgumentException e) {
			return false;
		}
	}
}
