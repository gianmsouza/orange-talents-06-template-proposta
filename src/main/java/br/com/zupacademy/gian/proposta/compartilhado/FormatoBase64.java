package br.com.zupacademy.gian.proposta.compartilhado;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {Base64Validator.class})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface FormatoBase64 {
	
    String message() default "{Campo precisa estar no formato Base64}";
    
    Class<?>[] groups() default {};
    
    Class<? extends Payload>[] payload() default {};
}