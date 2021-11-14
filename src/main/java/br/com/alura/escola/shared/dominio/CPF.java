package br.com.alura.escola.shared.dominio;

import java.util.Objects;

public class CPF {

    private String cpf;

    public CPF(String cpf) {
        if (cpf != null && !cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}")) {
            throw new IllegalArgumentException("CPF inválido.");
        }
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    @Override
    public String toString() {
        return cpf;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (o.getClass() == String.class) {
            String cpf1 = (String) o;
            return cpf1.equals(cpf);
        }
        CPF cpf1 = (CPF) o;
        return Objects.equals(cpf, cpf1.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }
}
