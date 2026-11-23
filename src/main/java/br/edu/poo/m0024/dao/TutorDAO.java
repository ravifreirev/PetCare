package br.edu.poo.m0024.dao;
import br.edu.poo.m0024.database.Conexao;
import br.edu.poo.m0024.model.Tutor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TutorDAO {

    public void cadastrar(Tutor tutor) {
        String sql = "INSERT INTO tutor (nome, cpf, telefone, cep, endereco, cidade, estado) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conexao = Conexao.connect();
        PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, tutor.getNome());
            stmt.setString(2, tutor.getCpf());
            stmt.setString(3, tutor.getTelefone());
            stmt.setString(4, tutor.getCep());
            stmt.setString(5, tutor.getEndereco());
            stmt.setString(6, tutor.getCidade());
            stmt.setString(7, tutor.getEstado());

            stmt.executeUpdate();

    } catch (SQLException e) {
            throw new RuntimeException(e);
        }

  }

  public void listar() {

        String sql = "SELECT * FROM tutor;";

        try (Connection conexao = Conexao.connect();
        PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet resultado = stmt.executeQuery()) {

            while (resultado.next()) {

                System.out.println("ID: " + resultado.getInt("id"));
                System.out.println("Nome: " + resultado.getString("nome"));
                System.out.println("CPF: " + resultado.getString("cpf"));
                System.out.println("Telefone: " + resultado.getString("telefone"));
                System.out.println("CEP: " + resultado.getString("cep"));
                System.out.println("Endereço: " + resultado.getString("endereco"));
                System.out.println("Cidade: " + resultado.getString("cidade"));
                System.out.println("Estado: " + resultado.getString("estado"));
                System.out.println("-------------------------");

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
  }

  public void atualizar (Tutor tutor) {

        String sql = "UPDATE tutor SET nome = ?, cpf = ?, telefone = ?, cep = ?, endereco = ?, cidade = ?, estado =? WHERE id = ?";

        try (Connection conexao = Conexao.connect();
        PreparedStatement stmt = conexao.prepareStatement(sql)){

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

  public void excluir(int id) {

        String sql = "DELETE FROM tutor WHERE id = ?";

        try (Connection conexao = Conexao.connect();
        PreparedStatement stmt = conexao.prepareStatement(sql)){

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
  }

}
