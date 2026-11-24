
package br.edu.poo.m0024.dao;

import br.edu.poo.m0024.database.Conexao;
import br.edu.poo.m0024.model.Tutor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TutorDAO {

    // ================= CADASTRAR =================

    public void cadastrar(Tutor tutor) {

        String sqlVerificar = "SELECT id FROM tutor WHERE cpf = ?";
        String sqlInserir = "INSERT INTO tutor (nome, cpf, telefone, cep, endereco, cidade, estado) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conexao = Conexao.connect();
             PreparedStatement consulta = conexao.prepareStatement(sqlVerificar)) {

            // Verifica se o CPF já está cadastrado
            consulta.setString(1, tutor.getCpf());

            try (ResultSet resultado = consulta.executeQuery()) {

                if (resultado.next()) {
                    throw new IllegalArgumentException(
                            "Já existe um tutor cadastrado com esse CPF."
                    );
                }
            }

            // Se não existir, realiza o cadastro
            try (PreparedStatement stmt = conexao.prepareStatement(sqlInserir)) {

                stmt.setString(1, tutor.getNome());
                stmt.setString(2, tutor.getCpf());
                stmt.setString(3, tutor.getTelefone());
                stmt.setString(4, tutor.getCep());
                stmt.setString(5, tutor.getEndereco());
                stmt.setString(6, tutor.getCidade());
                stmt.setString(7, tutor.getEstado());

                stmt.executeUpdate();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // ================= LISTAR =================

    public void listar() {

        String sql = "SELECT * FROM tutor ORDER BY id";

        try (Connection conexao = Conexao.connect();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet resultado = stmt.executeQuery()) {

            boolean encontrou = false;

            while (resultado.next()) {

                encontrou = true;

                String telefone = resultado.getString("telefone");

                if (telefone == null || telefone.isBlank()) {
                    telefone = "Não informado";
                }

                System.out.println("=================================");
                System.out.println("          DADOS DO TUTOR");
                System.out.println("=================================");
                System.out.println("ID: " + resultado.getInt("id"));
                System.out.println("Nome: " + resultado.getString("nome"));
                System.out.println("CPF: " + resultado.getString("cpf"));
                System.out.println("Telefone: " + telefone);
                System.out.println("CEP: " + resultado.getString("cep"));
                System.out.println("Endereço: " + resultado.getString("endereco"));
                System.out.println("Cidade: " + resultado.getString("cidade"));
                System.out.println("Estado: " + resultado.getString("estado"));
                System.out.println("=================================");
                System.out.println();
            }

            if (!encontrou) {
                System.out.println("Nenhum tutor cadastrado.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // ================= BUSCAR POR ID =================

    public Tutor buscarPorId(int id) {

        String sql = "SELECT * FROM tutor WHERE id = ?";

        try (Connection conexao = Conexao.connect();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet resultado = stmt.executeQuery()) {

                if (resultado.next()) {
                    return new Tutor(
                            resultado.getInt("id"),
                            resultado.getString("nome"),
                            resultado.getString("cpf"),
                            resultado.getString("telefone"),
                            resultado.getString("cep"),
                            resultado.getString("endereco"),
                            resultado.getString("cidade"),
                            resultado.getString("estado")
                    );
                }
            }

            return null;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // ================= VERIFICAR CPF DUPLICADO =================

    public boolean cpfJaCadastrado(String cpf, int idIgnorado) {

        String sql = "SELECT id FROM tutor WHERE cpf = ? AND id <> ?";

        try (Connection conexao = Conexao.connect();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, cpf);
            stmt.setInt(2, idIgnorado);

            try (ResultSet resultado = stmt.executeQuery()) {
                return resultado.next();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // ================= ATUALIZAR =================

    public void atualizar(Tutor tutor) {

        String sql = "UPDATE tutor SET nome = ?, cpf = ?, telefone = ?, cep = ?, endereco = ?, cidade = ?, estado = ? WHERE id = ?";

        try (Connection conexao = Conexao.connect();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, tutor.getNome());
            stmt.setString(2, tutor.getCpf());
            stmt.setString(3, tutor.getTelefone());
            stmt.setString(4, tutor.getCep());
            stmt.setString(5, tutor.getEndereco());
            stmt.setString(6, tutor.getCidade());
            stmt.setString(7, tutor.getEstado());
            stmt.setInt(8, tutor.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // ================= EXCLUIR =================

    public void excluir(int id) {

        String sql = "DELETE FROM tutor WHERE id = ?";

        try (Connection conexao = Conexao.connect();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
