package br.com.alura.escola.academico.dominio.aluno;

import java.util.regex.Pattern;

public class Email {

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    private String endereco;

    public Email(String endereco) {
        if (endereco == null || !VALID_EMAIL_ADDRESS_REGEX.asPredicate().test(endereco)) {
            throw new IllegalArgumentException("Endereco de e-mail inválido.");
        }
        this.endereco = endereco;
    }

    public String getEndereco() {
        return this.endereco;
    }
}
