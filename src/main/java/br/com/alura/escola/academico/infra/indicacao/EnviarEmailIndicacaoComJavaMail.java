package br.com.alura.escola.academico.infra.indicacao;

import br.com.alura.escola.academico.aplicacao.indicacao.EnviarEmailIndicacao;
import br.com.alura.escola.academico.dominio.aluno.Aluno;

public class EnviarEmailIndicacaoComJavaMail  implements EnviarEmailIndicacao {

    @Override
    public void enviarEmailPara(Aluno aluno) {
        System.out.println("Enviando email para " + aluno.getNome());
    }

}
