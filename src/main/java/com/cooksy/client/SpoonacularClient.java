package com.cooksy.client;

import com.cooksy.configuration.SpoonacularConfig;
import com.cooksy.exception.ApiRequestException;
import com.cooksy.model.api.SpCuParameters;
import com.cooksy.model.api.SpCuRecipeDetails;
import com.cooksy.model.api.SpCuRecipes;
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

    private final SpoonacularConfig spoonacularConfig;
    private final HttpClient httpClient;

    public SpoonacularClient(SpoonacularConfig spoonacularConfig) {
        this.spoonacularConfig = spoonacularConfig;
        this.httpClient =  HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .build();
    }

    public SpCuRecipeDetails getSpCuRecipeDetails(String id) throws
            IOException, InterruptedException, URISyntaxException {
        String urlDetails = ApiURL.DETAILS.getUrl(id) + ApiURL.INFORMATION.getUrl("%s");
        HttpResponse<String> response = callSpoonacularApi(urlDetails);
        return deserialize(response.body(), SpCuRecipeDetails.class);
    }

    public SpCuRecipes getSpCuRecipes(SpCuParameters spCuParameters) throws
            IOException, InterruptedException, URISyntaxException {
        String recipesUrl = ApiURL.RECIPES.getUrl("%s")
                + ApiURL.PAGE.getUrl(getRecipesPage(spCuParameters.getStart()))
                + ApiURL.INGREDIENT.getUrl(spCuParameters.getIngredients())
                + ApiURL.EQUIPMENT.getUrl(spCuParameters.getEquipments())
                + ApiURL.TYPE.getUrl(spCuParameters.getTypes());

        HttpResponse<String> response = callSpoonacularApi(recipesUrl);
        return deserialize(response.body(), SpCuRecipes.class);
    }

    private HttpResponse<String> callSpoonacularApi(String spoonacularApiUrl) throws URISyntaxException,
            IOException, InterruptedException {
        HttpResponse<String> httpResponse;
        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(new URI(String.format(spoonacularApiUrl, spoonacularConfig.getSpoonacular().getApiKey())))
                .GET()
                .build();
        httpResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());

        if (httpResponse.statusCode() == 402) {
            throw new ApiRequestException("API Spoonacular request limit reached");
        }
        return httpResponse;
    }

    private <T> T deserialize(String body, Class<T> tClass) throws JsonProcessingException {
        return new ObjectMapper().readValue(body, tClass);
    }

    private String getRecipesPage(String page) {
        if (page != null) {
            int valuePage = Integer.parseInt(page) - 1;
            return  String.format("%d", valuePage * 10);
        }
        return "";
    }
}
