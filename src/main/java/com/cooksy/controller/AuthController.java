package com.cooksy.controller;

import com.cooksy.dto.CredentialsDto;
import com.cooksy.dto.Id;
import com.cooksy.dto.UserDto;
import com.cooksy.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "http://localhost:4200",allowCredentials="true")
@RestController
@AllArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping(value = "/register", consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(CREATED)
    public void register(@RequestBody UserDto userDto) {
        userService.register(userDto);
    }


    @PostMapping(value = "/login", consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    public void login (@RequestBody CredentialsDto credentialsDto, HttpSession httpSession) {
        Authentication authentication = userService.login(credentialsDto);
        httpSession.setAttribute("userID", Id.idFromLong(userService.getUserByNick(credentialsDto.getNick()).getUserId()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        System.out.println("Post działa");
        System.out.println(authentication.getDetails());
        System.out.println(authentication.isAuthenticated());


    }
}
