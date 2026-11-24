
package br.edu.poo.m0024.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import io.github.cdimascio.dotenv.Dotenv;

public class Conexao {

    public static Connection connect() {

        Dotenv dotenv = Dotenv.load();

        String url = dotenv.get("DB_URL");
        String usuario = dotenv.get("DB_USER");
        String senha = dotenv.get("DB_PASSWORD");

        try {
            // Desativa a thread de limpeza do MySQL Connector/J.
            // Os DAOs fecham explicitamente todas as conexões.
            System.setProperty(
                    "com.mysql.cj.disableAbandonedConnectionCleanup",
                    "true"
            );

            return DriverManager.getConnection(url, usuario, senha);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
