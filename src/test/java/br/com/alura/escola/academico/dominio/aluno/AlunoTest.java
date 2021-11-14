package br.com.alura.escola.academico.dominio.aluno;

import br.com.alura.escola.shared.dominio.CPF;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AlunoTest {

    @Test
    public void deveriaPermitirCadastrarUmTelefone() {
        Aluno aluno = new Aluno(
                new CPF("123.456.789-00"),
                "fulano",
                new Email("fulano@email.com")
        );
        aluno.adicionarTelefone("12", "12345678");
        assertEquals(1, aluno.getTelefones().size());
    }

    @Test
    public void deveriaPermitirCadastrarDoisTelefones() {
        Aluno aluno = new Aluno(
                new CPF("123.456.789-00"),
                "fulano",
                new Email("fulano@email.com")
        );
        aluno.adicionarTelefone("12", "12345678");
        aluno.adicionarTelefone("12", "12345678");
        assertEquals(2, aluno.getTelefones().size());
    }

    @Test
    public void naoDeveriaPermitirCadastrarTresTelefones() {
        Aluno aluno = new Aluno(
                new CPF("123.456.789-00"),
                "fulano",
                new Email("fulano@email.com")
        );
        aluno.adicionarTelefone("12", "12345678");
        aluno.adicionarTelefone("12", "12345678");
        assertThrows(IllegalArgumentException.class, () -> aluno.adicionarTelefone("12", "12345678"));
    }

}