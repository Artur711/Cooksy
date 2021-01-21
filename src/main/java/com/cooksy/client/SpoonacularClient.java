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

    public <T> T getObject(Class<T> tClass, String spoonacularApiUrl) throws URISyntaxException,
            IOException, InterruptedException {
        HttpResponse<String> httpResponse;
        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(new URI(String.format(spoonacularApiUrl, apiKeyReader.getKey())))
                .GET()
                .build();
        httpResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());

        if (httpResponse.statusCode() == 402) {
            apiKeyReader.next();
        }
        return deserialize(httpResponse.body(), tClass);
    }

    private <T> T deserialize(String body, Class<T> tClass) throws JsonProcessingException {
        return new ObjectMapper().readValue(body, tClass);
    }
}
