package com.cooksy.client;

import com.cooksy.model.api.SpCuParameters;
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
        this.httpClient =  HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .build();
    }

    public SpCuRecipeDetails getSpCuRecipeDetails(String id) throws
            IOException, InterruptedException, URISyntaxException {
        String url = String.format(ApiURL.DETAILS.getUrl(), id, "%s");
        HttpResponse<String> response = callSpoonacularApi(url);
        return deserialize(response.body(), SpCuRecipeDetails.class);
    }

    public SpCuRecipes getSpCuRecipes(SpCuParameters spCuParameters) throws
            IOException, InterruptedException, URISyntaxException {
        String recipesUrl = (spCuParameters.getStart() == null) ?
                ApiURL.RECIPES.getUrl() : getRecipesPage(spCuParameters.getStart(), ApiURL.RECIPES.getUrl());

        if (spCuParameters.getIngredients() != null) {
            recipesUrl = recipesUrl + String.format(ApiURL.INGREDIENT.getUrl(), replaceSpaces(spCuParameters.getIngredients()));
        }
        if (spCuParameters.getEquipments() != null) {
            recipesUrl = recipesUrl + String.format(ApiURL.EQUIPMENT.getUrl(), replaceSpaces(spCuParameters.getEquipments()));
        }
        if (spCuParameters.getTypes() != null) {
            recipesUrl = recipesUrl + String.format(ApiURL.TYPE.getUrl(), replaceSpaces(spCuParameters.getTypes()));
        }

        HttpResponse<String> response = callSpoonacularApi(recipesUrl);
        return deserialize(response.body(), SpCuRecipes.class);
    }

    private HttpResponse<String> callSpoonacularApi(String spoonacularApiUrl) throws URISyntaxException,
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
        return httpResponse;
    }

    private <T> T deserialize(String body, Class<T> tClass) throws JsonProcessingException {
        return new ObjectMapper().readValue(body, tClass);
    }

    private String getRecipesPage(String page, String url) {
        int valuePage = Integer.parseInt(page) - 1;
        return url + String.format(ApiURL.PAGE.getUrl(), valuePage * 10);
    }

    private String replaceSpaces(String str) {
        return str.replaceAll(" ", "+");
    }
}
