package com.cooksy.client;
import com.cooksy.configuration.ApplicationProperties;
import com.cooksy.model.api.KrogerToken;
import com.cooksy.util.enums.ApiURL;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

@Service
public class KrogerClient {

    private final HttpClient httpClient;
    private final ApplicationProperties applicationProperties;
    private HttpRequest getRequest;
    private HttpResponse<String> httpResponse;
    private KrogerToken token;

    public KrogerClient(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
        this.httpClient =  HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .build();
        this.token = getToken();
    }

    public <T> T getKrogerProducts(String product, String page, Class<T> tClass) {
        String krogerApiUrl = "https://api.kroger.com/v1/products?filter.term=%s&filter.fulfillment=sth&filter.start=%d&filter.limit=5";

        try {
            getRequest = HttpRequest.newBuilder()
                    .uri(new URI(String.format(krogerApiUrl, product, getProductPage(page))))
                    .headers(HttpHeaders.ACCEPT, "application/json")
                    .headers(HttpHeaders.AUTHORIZATION, String.format("Bearer %s", token.getToken()))
                    .GET()
                    .build();
            httpResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());
        }
        catch (URISyntaxException | IOException | InterruptedException error) {
            System.out.println(error.getMessage());
            return (T) error.getMessage();
        }

        if (httpResponse.statusCode() == 401 && !token.isEmpty()) {
            this.token = getToken();

            if (!token.isEmpty()) {
                return getKrogerProducts(product, page, tClass);
            }
        }
        return deserialize(httpResponse.body(), tClass);
    }

    private KrogerToken getToken() {
        String clientId = applicationProperties.getKroger().getClientId();
        String clientSecret = applicationProperties.getKroger().getClientSecret();

        String grantType = String.format("grant_type=client_credentials&scope=%s", applicationProperties.getKroger().getScope());
        HttpRequest.BodyPublisher body = HttpRequest.BodyPublishers.ofString(grantType);

        try {
            String encodedData = DatatypeConverter.printBase64Binary((clientId + ":" + clientSecret).getBytes(StandardCharsets.UTF_8));
            getRequest = HttpRequest.newBuilder()
                    .uri(new URI("https://api.kroger.com/v1/connect/oauth2/token"))
                    .headers("Content-Type", "application/x-www-form-urlencoded")
                    .headers("Authorization", "Basic " + encodedData)
                    .POST(body)
                    .build();
            httpResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());

            return deserialize(httpResponse.body(), KrogerToken.class);
        } catch (URISyntaxException | InterruptedException | IOException error) {
            System.out.println(error.getMessage());
            return new KrogerToken();
        }
    }

    private <T> T deserialize(String body, Class<T> tClass) {
        try {
            return new ObjectMapper().readValue(body, tClass);
        } catch (JsonProcessingException e) {
            return (T) e.getMessage();
        }
    }

    private Integer getProductPage(String page) {
        return  (Integer.parseInt(page) - 1) * 5 + 1;
    }
}
