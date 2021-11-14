package br.com.alura.escola.academico.aplicacao.aluno.matricular;

import br.com.alura.escola.shared.dominio.evento.PublicadorDeEventos;
import br.com.alura.escola.academico.dominio.aluno.Aluno;
import br.com.alura.escola.shared.dominio.CPF;
import br.com.alura.escola.academico.infra.aluno.RepositorioDeAlunosEmMemoria;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatricularAlunoTest {

    @Test
    void alunoDeveriaSerPersistido() {
        RepositorioDeAlunosEmMemoria repositorioDeAlunos = new RepositorioDeAlunosEmMemoria();
        PublicadorDeEventos publicadorDeEventos = new PublicadorDeEventos();
        MatricularAluno useCase = new MatricularAluno(repositorioDeAlunos, publicadorDeEventos);

        MatricularAlunoDto fulano = new MatricularAlunoDto("Fulano", "123.456.789-00", "fulano@email.com");
        useCase.matricular(fulano);

        Aluno aluno = repositorioDeAlunos.buscarPorCpf(new CPF("123.456.789-00"));

        assertEquals("Fulano", aluno.getNome());
        assertEquals("123.456.789-00", aluno.getCpf().getCpf());
        assertEquals("fulano@email.com", aluno.getEmail());
    }

}