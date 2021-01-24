package com.cooksy.client;
import com.cooksy.model.api.KrogerResult;
import com.cooksy.model.api.KrogerToken;
import com.cooksy.service.KrogerCredentialsReader;
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
    private final KrogerCredentialsReader reader;
    private HttpRequest getRequest;
    private HttpResponse<String> httpResponse;
    private KrogerToken token;

    public KrogerClient(KrogerCredentialsReader reader) throws
            InterruptedException, IOException, URISyntaxException {
        this.reader = reader;
        this.httpClient =  HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .build();
        this.token = getToken();
    }

    public KrogerResult getKrogerProducts(String product, String page) throws
            URISyntaxException, IOException, InterruptedException {
        String url = "https://api.kroger.com/v1/products?filter.term=%s&filter.fulfillment=sth&filter.start=%d&filter.limit=5";

        getRequest = HttpRequest.newBuilder()
                .uri(new URI(String.format(url, product, getProductPage(page))))
                .headers(HttpHeaders.ACCEPT, "application/json")
                .headers(HttpHeaders.AUTHORIZATION, String.format("Bearer %s", token.getToken()))
                .GET()
                .build();
        httpResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());

        if (httpResponse.statusCode() == 401 && !token.isEmpty()) {
            this.token = getToken();

            if (!token.isEmpty()) {
                return getKrogerProducts(product, page);
            }
        }
        return deserialize(httpResponse.body(), KrogerResult.class);
    }

    private KrogerToken getToken() throws IOException, InterruptedException, URISyntaxException {
        String clientId = reader.getClientId();
        String clientSecret = reader.getClientSecret();

        String grantType = String.format("grant_type=client_credentials&scope=%s", reader.getScope());
        HttpRequest.BodyPublisher body = HttpRequest.BodyPublishers.ofString(grantType);

        String encodedData = DatatypeConverter.printBase64Binary((clientId + ":" + clientSecret)
                .getBytes(StandardCharsets.UTF_8));
        getRequest = HttpRequest.newBuilder()
                .uri(new URI("https://api.kroger.com/v1/connect/oauth2/token"))
                .headers("Content-Type", "application/x-www-form-urlencoded")
                .headers("Authorization", "Basic " + encodedData)
                .POST(body)
                .build();
        httpResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());

        return deserialize(httpResponse.body(), KrogerToken.class);
    }

    private <T> T deserialize(String body, Class<T> tClass) throws JsonProcessingException {
        return new ObjectMapper().readValue(body, tClass);
    }

    private Integer getProductPage(String page) {
        return  (Integer.parseInt(page) - 1) * 5 + 1;
    }
}
