package com.cooksy.controller;

import com.cooksy.dto.CredentialsDto;
import com.cooksy.dto.Id;
import com.cooksy.dto.UserDto;
import com.cooksy.model.JwtResponse;
import com.cooksy.service.UserService;
import com.cooksy.util.JwtUtils;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "http://localhost:4200",allowCredentials="true")
@RestController
@AllArgsConstructor
public class AuthController {

    private final UserService userService;
    private final JwtUtils jwtUtils;

    @PostMapping(value = "/register", consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(CREATED)
    public void register(@RequestBody UserDto userDto)   {
        userService.register(userDto);
    }


    @PostMapping(value = "/login", consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    public JwtResponse login (@RequestBody CredentialsDto credentialsDto) {
        Authentication authentication = userService.login(credentialsDto);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new JwtResponse(jwtUtils.generateJwtToken(authentication));
    }

    public void logout() {
        SecurityContextHolder.getContext().setAuthentication(null);
    }
}
