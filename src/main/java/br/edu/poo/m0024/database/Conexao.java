package br.edu.poo.m0024.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import io.github.cdimascio.dotenv.Dotenv;

public class Conexao {

    public static Connection connect() {

        Dotenv dotenv;

        try {
            // Permite iniciar a leitura mesmo se o .env não existir.
            // Abaixo verificamos as configurações e exibimos uma mensagem própria.
            dotenv = Dotenv.configure()
                    .ignoreIfMissing()
                    .load();

        } catch (RuntimeException e) {
            throw new RuntimeException(
                    "Não foi possível ler o arquivo .env. "
                            + "Verifique sua configuração conforme o README.",
                    e
            );
        }

        String url = dotenv.get("DB_URL");
        String usuario = dotenv.get("DB_USER");
        String senha = dotenv.get("DB_PASSWORD");

        // URL e usuário são obrigatórios.
        if (url == null || url.isBlank()
                || usuario == null || usuario.isBlank()) {

            throw new IllegalStateException(
                    "Configuração do banco incompleta. "
                            + "Verifique DB_URL e DB_USER no arquivo .env."
            );
        }

        // Uma senha vazia é permitida, dependendo da configuração do MySQL.
        if (senha == null) {
            senha = "";
        }

        try {
            // Desativa a thread de limpeza do MySQL Connector/J.
            // Os DAOs fecham explicitamente todas as conexões.
            System.setProperty(
                    "com.mysql.cj.disableAbandonedConnectionCleanup",
                    "true"
            );

            return DriverManager.getConnection(url, usuario, senha);

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Não foi possível conectar ao MySQL. "
                            + "Verifique se o servidor está ligado e se "
                            + "DB_URL, DB_USER e DB_PASSWORD estão corretos. "
                            + "Detalhes: " + e.getMessage(),
                    e
            );
        }
    }
}