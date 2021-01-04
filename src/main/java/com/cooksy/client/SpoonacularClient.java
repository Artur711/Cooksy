package com.cooksy.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class SpoonacularClient {

    private static final String API_KEY = "apiKey=b8ed5d01de5a4b1991459f7bf8010258";
    private final HttpClient httpClient;

    public SpoonacularClient() {
        this.httpClient =  HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .build();
    }

    public <T> T getObject(Class<T> tClass, String spoonacularApiUrl) {
        try {
            HttpRequest getRequest = HttpRequest.newBuilder()
                    .uri(new URI(String.format(spoonacularApiUrl, API_KEY)))
                    .GET()
                    .build();
            HttpResponse<String> httpResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());

            return deserialize(httpResponse.body(), tClass);
        } catch (URISyntaxException | IOException | InterruptedException e) {
            return (T) e.getMessage();
        }
    }

    private <T> T deserialize(String body, Class<T> tClass) {
        try {
            return new ObjectMapper().readValue(body, tClass);
        } catch (JsonProcessingException e) {
            return (T) e.getMessage();
        }
    }
}
