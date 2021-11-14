package br.com.alura.escola.academico.dominio.aluno;

import br.com.alura.escola.shared.dominio.CPF;

public class FabricaDeAluno {

    private Aluno aluno;

    public FabricaDeAluno comNomeCpfEEmail(String nome, String cpf, String email) {
        this.aluno = new Aluno(new CPF(cpf), nome, new Email(email));
        return this;
    }

    public FabricaDeAluno comTelefone(String ddd, String numero) {
        this.aluno.adicionarTelefone(ddd, numero);
        return this;
    }

    public Aluno aluno() {
        return this.aluno;
    }
}
