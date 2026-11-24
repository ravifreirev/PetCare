
package br.edu.poo.m0024.api;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpTimeoutException;
import java.time.Duration;

public class ViaCepService {

    private static final Gson gson = new Gson();

    private final HttpClient client = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(5))
            .build();

    public ViaCepResposta consultarCep(String cep) {

        String url = "https://viacep.com.br/ws/" + cep + "/json/";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(Duration.ofSeconds(10))
                .GET()
                .build();

        try {
            HttpResponse<String> response = client.send(
                    request,
                    HttpResponse.BodyHandlers.ofString()
            );

            // Verifica se a resposta HTTP foi bem-sucedida
            if (response.statusCode() < 200 || response.statusCode() >= 300) {
                throw new RuntimeException(
                        "O ViaCEP respondeu com erro HTTP: " + response.statusCode()
                );
            }

            // Converte o JSON recebido para um objeto Java
            return gson.fromJson(response.body(), ViaCepResposta.class);

        } catch (HttpTimeoutException e) {
            throw new RuntimeException(
                    "Tempo limite excedido ao consultar o ViaCEP.", e
            );

        } catch (JsonParseException e) {
            throw new RuntimeException(
                    "O ViaCEP retornou uma resposta em formato inválido.", e
            );

        } catch (IOException e) {
            throw new RuntimeException(
                    "Não foi possível conectar ao ViaCEP: " + e.getMessage(), e
            );

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(
                    "Consulta ao ViaCEP interrompida.", e
            );
        }
    }

    // Fecha o cliente HTTP ao encerrar o programa
    public void fechar() {
        client.close();
    }
}
