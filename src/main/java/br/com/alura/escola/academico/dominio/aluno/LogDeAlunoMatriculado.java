package br.com.alura.escola.academico.dominio.aluno;

import br.com.alura.escola.shared.dominio.evento.Evento;
import br.com.alura.escola.shared.dominio.evento.Ouvinte;

import java.time.format.DateTimeFormatter;

public class LogDeAlunoMatriculado extends Ouvinte {

    @Override
    protected boolean deveProcessar(Evento evento) {
        return evento instanceof AlunoMatriculado;
    }

    @Override
    protected void reageAo(Evento evento) {
        this.reageAo((AlunoMatriculado) evento);
    }

    public void reageAo(AlunoMatriculado evento){
        String momentoFormatado = evento.momento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        System.out.printf("Aluno com cpf %s matriculado em %s.%n", evento.getCpfDoAluno(), momentoFormatado);
    }

}
