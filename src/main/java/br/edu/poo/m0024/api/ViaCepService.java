package br.edu.poo.m0024.api;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.IOException;
import com.google.gson.Gson;

public class ViaCepService {

    private final HttpClient client = HttpClient.newHttpClient();

    public ViaCepResposta consultarCep(String cep) {
        String url = "https://viacep.com.br/ws/" + cep + "/json/";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        try {
            HttpResponse<String> response = client.send(
                    request,
                    HttpResponse.BodyHandlers.ofString()
            );

            Gson gson = new Gson();

            ViaCepResposta dados = gson.fromJson(
                    response.body(),
                    ViaCepResposta.class
            );

            return dados;

        } catch (IOException e) {
            throw new RuntimeException("Erro ao consultar o ViaCEP: " + e.getMessage());

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Consulta ao ViaCEP interrompida.", e);
        }

    }
}
