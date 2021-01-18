package com.cooksy.util;


import com.cooksy.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class JwtUtils {

    public String generateJwtToken(Authentication authentication) {
        User user = (User) authentication.getPrincipal();


    }

}
