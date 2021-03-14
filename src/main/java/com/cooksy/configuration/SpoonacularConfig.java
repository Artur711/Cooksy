package com.cooksy.configuration;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "api-recipes")
@Getter
public class SpoonacularConfig {

    private final Spoonacular spoonacular = new Spoonacular();

    @Getter
    public static class Spoonacular {

        private String apiKey;

        public void setApiKey(String apiKey) {
            this.apiKey = String.format("apiKey=%s", apiKey);
        }
    }
}
