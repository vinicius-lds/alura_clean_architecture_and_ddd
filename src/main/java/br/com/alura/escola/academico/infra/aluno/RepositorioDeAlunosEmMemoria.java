package br.com.alura.escola.academico.infra.aluno;

import br.com.alura.escola.academico.dominio.aluno.Aluno;
import br.com.alura.escola.shared.dominio.CPF;
import br.com.alura.escola.academico.dominio.aluno.RepositorioDeAlunos;

import java.util.ArrayList;
import java.util.List;

public class RepositorioDeAlunosEmMemoria implements RepositorioDeAlunos {

    private List<Aluno> alunos = new ArrayList<>();

    @Override
    public void matricular(Aluno aluno) {
        this.alunos.add(aluno);
    }

    @Override
    public Aluno buscarPorCpf(CPF cpf) {
        return this.alunos.stream()
                .filter(aluno -> aluno.getCpf().equals(cpf))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("Aluno com cpf %s não foi encontrado", cpf.getCpf())));
    }

    @Override
    public List<Aluno> listarTodosOsAlunosMatriculados() {
        return this.alunos;
    }
}
