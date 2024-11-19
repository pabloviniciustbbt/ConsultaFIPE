package com.pabloleal.ConsultaFIPE.Services;

import com.pabloleal.ConsultaFIPE.Exception.FipeException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiServices {


    public String apiRequest(String adress) {
        String json;

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(adress)).build();
            HttpResponse<String> response;
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            json = response.body();

            if (json.contains("error")) {
                throw new FipeException("\nStatus Code: 404 - Não foi possível localizar a informação solicitada!");
            }

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        } catch (FipeException e){
            System.out.println(e.getMessage());
            json = null;
        }
        return json;

    }


}
