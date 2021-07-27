package br.com.zupacademy.gian.proposta.seguranca;

import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;

public class CriptografaDocumento {

	private static final String salt = "e606bfd5cf9f198e"; 
	private static final String senha = "password"; 

	public static String encrypt(String documento) {
        //TextEncryptor textEncryptor = Encryptors.text(senha, salt);
		TextEncryptor textEncryptor = Encryptors.queryableText(senha, salt);
        return textEncryptor.encrypt(documento);
    }

    public static String decrypt(String documentoEncriptado) {
        //TextEncryptor textEncryptor = Encryptors.text(senha, salt);
        TextEncryptor textEncryptor = Encryptors.queryableText(senha, salt);
        return textEncryptor.decrypt(documentoEncriptado);
    }
}
