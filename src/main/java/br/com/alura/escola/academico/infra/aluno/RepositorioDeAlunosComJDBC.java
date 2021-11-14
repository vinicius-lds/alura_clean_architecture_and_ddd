package br.com.alura.escola.academico.infra.aluno;

import br.com.alura.escola.academico.dominio.aluno.*;
import br.com.alura.escola.shared.dominio.CPF;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RepositorioDeAlunosComJDBC implements RepositorioDeAlunos {

    private Connection connection;

    public RepositorioDeAlunosComJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void matricular(Aluno aluno) {
        String sql = "INSERT INTO aluno VALUES (?, ?, ?);";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(0, aluno.getCpf().getCpf());
            ps.setString(1, aluno.getNome());
            ps.setString(2, aluno.getEmail());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        sql = "INSERT INTO telefone VALUES (?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            for (Telefone telefone : aluno.getTelefones()) {
                ps.setString(0, telefone.getDdd());
                ps.setString(1, telefone.getNumero());
                ps.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Aluno buscarPorCpf(CPF cpf) {
        FabricaDeAluno fabricaDeAluno = new FabricaDeAluno();
        String idAluno = null;
        String sql = "SELECT id, nome, email FROM aluno WHERE cpf = ?;";
        try (PreparedStatement ps = this.connection.prepareStatement(sql)) {
            ps.setString(0, cpf.getCpf());
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                throw new IllegalArgumentException(String.format("Aluno com cpf %s não foi encontrado", cpf.getCpf()));
            }

            idAluno = rs.getString("id");
            fabricaDeAluno = fabricaDeAluno
                    .comNomeCpfEEmail(rs.getString("nome"), cpf.getCpf(), rs.getString("email"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        sql = "SELECT ddd, numero FROM telefone WHERE aluno_id = ?;";
        try (PreparedStatement ps = this.connection.prepareStatement(sql)) {
            ps.setString(0, idAluno);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                fabricaDeAluno = fabricaDeAluno
                        .comTelefone(rs.getString("ddd"), rs.getString("numero"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return fabricaDeAluno.aluno();
    }

    @Override
    public List<Aluno> listarTodosOsAlunosMatriculados() {
        List<Aluno> alunos = new ArrayList<>();
        String sql = "SELECT id, nome, cpf, email FROM aluno;";
        try (PreparedStatement ps = this.connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                FabricaDeAluno fabricaDeAluno = new FabricaDeAluno();
                String idAluno = rs.getString("id");
                fabricaDeAluno = fabricaDeAluno
                        .comNomeCpfEEmail(rs.getString("nome"), rs.getString("cpf"), rs.getString("email"));
                sql = "SELECT ddd, numero FROM telefone WHERE aluno_id = ?;";
                try (PreparedStatement psTelefone = this.connection.prepareStatement(sql)) {
                    psTelefone.setString(0, idAluno);
                    ResultSet rsTelefone = psTelefone.executeQuery();
                    while (rsTelefone.next()) {
                        fabricaDeAluno = fabricaDeAluno
                                .comTelefone(rsTelefone.getString("ddd"), rsTelefone.getString("numero"));
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                alunos.add(fabricaDeAluno.aluno());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return alunos;
    }

}
