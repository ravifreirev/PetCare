package br.edu.poo.m0024.dao;
import br.edu.poo.m0024.database.Conexao;
import br.edu.poo.m0024.model.Pet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PetDAO {

    public void cadastrar(Pet pet) {

        String sql = "INSERT INTO pet (nome, idade, especie, raca, sexo, tutor_id) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conexao = Conexao.connect();
        PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, pet.getNome());
            stmt.setInt(2, pet.getIdade());
            stmt.setString(3, pet.getEspecie());
            stmt.setString(4, pet.getRaca());
            stmt.setString(5, pet.getSexo());
            stmt.setInt(6, pet.getTutorId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void listar() {

        String sql = "SELECT * FROM pet;";

        try (Connection conexao = Conexao.connect();
        PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet resultado = stmt.executeQuery()) {

            while (resultado.next()) {

                System.out.println("ID: " + resultado.getInt("id"));
                System.out.println("Nome: " + resultado.getString("nome"));
                System.out.println("Idade: " + resultado.getInt("idade"));
                System.out.println("Espécie: " + resultado.getString("especie"));
                System.out.println("Raça: " + resultado.getString("raca"));
                System.out.println("Sexo: " + resultado.getString("sexo"));
                System.out.println("Id do tutor: " + resultado.getInt("tutor_id"));
                System.out.println("-------------------------");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Pet buscarPorId(int id) {
        String sql = "SELECT * FROM pet WHERE id = ?";

        try (Connection conexao = Conexao.connect();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet resultado = stmt.executeQuery()) {

                if (resultado.next()) {
                    return new Pet(
                            resultado.getInt("id"),
                            resultado.getString("nome"),
                            resultado.getString("raca"),
                            resultado.getString("especie"),
                            resultado.getString("sexo"),
                            resultado.getInt("idade"),
                            resultado.getInt("tutor_id")
                    );
                }

                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void atualizar(Pet pet) {

        String sql = "UPDATE pet SET nome = ?, idade = ?, especie = ?, raca = ?, sexo = ?, tutor_id = ? WHERE id = ?";

        try (Connection conexao = Conexao.connect();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, pet.getNome());
            stmt.setInt(2, pet.getIdade());
            stmt.setString(3, pet.getEspecie());
            stmt.setString(4, pet.getRaca());
            stmt.setString(5, pet.getSexo());
            stmt.setInt(6, pet.getTutorId());
            stmt.setInt(7, pet.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean existePetDoTutor(int tutorId) {

        String sql = "SELECT 1 FROM pet WHERE tutor_id = ? LIMIT 1";

        try (Connection conexao = Conexao.connect();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, tutorId);

            try (ResultSet resultado = stmt.executeQuery()) {
                return resultado.next();
            }

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Erro ao consultar pets: " + e.getMessage(), e
            );
        }
    }

    public void excluir(int id) {

        String sql = "DELETE FROM pet WHERE id = ?";

        try (Connection conexao = Conexao.connect();
        PreparedStatement stmt = conexao.prepareStatement(sql)){

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
