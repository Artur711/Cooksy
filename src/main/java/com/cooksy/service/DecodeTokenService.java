package com.cooksy.service;

import com.cooksy.exception.NotFoundException;
import com.cooksy.util.JwtUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class DecodeTokenService {

    private final JwtUtils jwtUtils;

    public Long getUserIdFromToken(String tokenValue) {
        Optional<String> tokenFromHeader = jwtUtils.getTokenFromHeader(tokenValue);
        String valueOfToken = "";
        if (tokenFromHeader.isPresent()) {
            valueOfToken = tokenFromHeader.get();
        }
        String userIDFromJwtToken = "O";
        try {
            userIDFromJwtToken = jwtUtils.getUsernameIDFromJwtToken(valueOfToken);
        } catch (RuntimeException e) {
            throw new NotFoundException("User");
        }
        return Long.parseLong(userIDFromJwtToken);
    }
}
