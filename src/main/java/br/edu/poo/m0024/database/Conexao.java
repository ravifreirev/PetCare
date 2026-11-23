package br.edu.poo.m0024.database;
import java.sql.Connection;
import java.sql.DriverManager;
import io.github.cdimascio.dotenv.Dotenv;
import java.sql.SQLException;

public class Conexao {

    private static Connection connection;

    public static Connection connect() {

        Dotenv dotenv = Dotenv.load();

        String url = dotenv.get("DB_URL");
        String usuario = dotenv.get("DB_USER");
        String senha = dotenv.get("DB_PASSWORD");

        try {
            connection = DriverManager.getConnection(url, usuario, senha);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return  connection;

    }
}
