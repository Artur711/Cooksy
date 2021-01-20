package com.cooksy.client;

import com.cooksy.model.api.SpCuRecipeDetails;
import com.cooksy.model.api.SpCuRecipes;
import com.cooksy.service.ApiKeyReader;
import com.cooksy.util.enums.ApiURL;
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
        this.httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .build();
    }

    public SpCuRecipeDetails getSpCuRecipeDetails(String id) {
        HttpResponse<String> response = callSpoonacularApi(id);
        return deserialize(response.body(), SpCuRecipeDetails.class);
    }

    public SpCuRecipes getSpCuRecipes() {
        HttpResponse<String> response = callSpoonacularApi();
        return deserialize(response.body(), SpCuRecipes.class);
    }

    private HttpResponse<String> callSpoonacularApi(String id) {
        String url = String.format(ApiURL.DETAILS.getUrl(), id, "%s");
        HttpResponse<String> httpResponse;
        try {
            HttpRequest getRequest = HttpRequest.newBuilder()
                    .uri(new URI(String.format(url, apiKeyReader.getKey())))
                    .GET()
                    .build();
            httpResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());

        } catch (URISyntaxException | IOException | InterruptedException e) {

        }
        if (httpResponse.statusCode() == 402) {
            apiKeyReader.next();
        }
        return httpResponse;
    }

    private <T> T deserialize(String body, Class<T> tClass) {
        try {
            return new ObjectMapper().readValue(body, tClass);
        } catch (JsonProcessingException e) {

        }
    }
}
