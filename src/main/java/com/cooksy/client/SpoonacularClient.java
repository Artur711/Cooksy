package com.cooksy.client;

import com.cooksy.service.ApiKeyReader;
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

    private final ApiKeyReader apiKeyReader;
    private final HttpClient httpClient;

    public SpoonacularClient(ApiKeyReader apiKeyReader) {
        this.apiKeyReader = apiKeyReader;
        this.httpClient =  HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .build();
    }

    public <T> T getObject(Class<T> tClass, String spoonacularApiUrl) {

        try {
            HttpRequest getRequest = HttpRequest.newBuilder()
                    .uri(new URI(String.format(spoonacularApiUrl, apiKeyReader.getKey())))
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
