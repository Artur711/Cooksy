package com.cooksy.util;


import com.cooksy.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

import static java.util.Optional.empty;

@Component
public class JwtUtils {

    private static final long JWT_EXPIRATION_MS = 3_600_000;
    private static final String BEARER_SCHEMA_NAME = "Bearer";
    private static final String JWT_SECRET = "Cooksy2020";

    public String generateJwtToken(Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject(user.getName())
                .setId(user.getUserId().toString())
                .setIssuedAt(new Date())
                .setExpiration(Date.from(new Date().toInstant().plusMillis(JWT_EXPIRATION_MS)))
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
    }


    public Optional<String> getTokenFromHeader(String authorizationHeaderValue) {
        if(authorizationHeaderValue != null && authorizationHeaderValue.startsWith(BEARER_SCHEMA_NAME)) {
            return Optional.of(authorizationHeaderValue.substring(7));
        }
        return empty();
    }

    public String getUsernameFromJwtToken(String token) {
        return Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
    public String getUsernameIDFromJwtToken(String token) {
        return Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token)
                .getBody()
                .getId();
    }
}
