package br.com.alura.escola.academico.dominio.aluno;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class TelefoneTest {

    @ParameterizedTest
    @CsvSource({ "abc,def", "444,444" })
    void naoDeveriaDeixarCriarCPFsInvalidos(String ddd, String numero) {
        assertThrows(IllegalArgumentException.class, () -> new Telefone(ddd, numero));
    }

    @ParameterizedTest
    @CsvSource({ "47,99887766", "47,998877665" })
    void deveriaDeixaCriarCPFsValidos(String ddd, String numero) {
        assertEquals(ddd, new Telefone(ddd, numero).getDdd());
        assertEquals(numero, new Telefone(ddd, numero).getNumero());
    }


}