package br.com.alura.escola.academico.dominio.aluno;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EmailTest {

    @ParameterizedTest
    @ValueSource(strings = { "", "emailInvalido" })
    void naoDeveriaDeixarCriarEmailsComEnderecosInvalidos(String endereco) {
        assertThrows(IllegalArgumentException.class, () -> new Email(endereco));
    }

    @ParameterizedTest
    @ValueSource(strings = { "teste@gmail.com" })
    void deveriaDeixaCriarEmailsComEnderecosValidos(String endereco) {
        var email = new Email(endereco);
        assertEquals(endereco, email.getEndereco());
    }

}