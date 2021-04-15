package com.cooksy.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "api-products")
@Getter
public class KrogerConfig {

    private final Kroger kroger = new Kroger();

    @Getter
    @Setter
    public static class Kroger {

        private String clientId;
        private String clientSecret;
        private String scope;
    }
}
