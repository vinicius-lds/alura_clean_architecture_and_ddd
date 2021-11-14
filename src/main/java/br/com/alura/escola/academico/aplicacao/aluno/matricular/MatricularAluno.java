package br.com.alura.escola.academico.aplicacao.aluno.matricular;

import br.com.alura.escola.shared.dominio.evento.PublicadorDeEventos;
import br.com.alura.escola.academico.dominio.aluno.*;
import br.com.alura.escola.shared.dominio.CPF;

public class MatricularAluno {

    private RepositorioDeAlunos repositorioDeAlunos;
    private PublicadorDeEventos publicadorDeEventos;

    public MatricularAluno(RepositorioDeAlunos repositorioDeAlunos, PublicadorDeEventos publicadorDeEventos) {
        this.repositorioDeAlunos = repositorioDeAlunos;
        this.publicadorDeEventos = publicadorDeEventos;
    }

    public void matricular(MatricularAlunoDto matricularAlunoDto) {
        Aluno novo = new Aluno(
                new CPF(matricularAlunoDto.getCpfAluno()),
                matricularAlunoDto.getNomeAluno(),
                new Email(matricularAlunoDto.getEmailAluno())
        );
        repositorioDeAlunos.matricular(novo);
        publicadorDeEventos.publicar(new AlunoMatriculado(novo.getCpf()));
    }
}
