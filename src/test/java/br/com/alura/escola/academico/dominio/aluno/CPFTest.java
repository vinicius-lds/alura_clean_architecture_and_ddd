package br.com.alura.escola.academico.dominio.aluno;

import br.com.alura.escola.shared.dominio.CPF;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class CPFTest {
    @ParameterizedTest
    @ValueSource(strings = { "", "631654164649649684984984" })
    void naoDeveriaDeixarCriarCPFsInvalidos(String cpf) {
        assertThrows(IllegalArgumentException.class, () -> new CPF(cpf));
    }

    @ParameterizedTest
    @ValueSource(strings = { "123.456.789-00" })
    void deveriaDeixaCriarCPFsValidos(String cpf) {
        assertEquals(cpf, new CPF(cpf).getCpf());
    }

}