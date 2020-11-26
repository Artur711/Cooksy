package com.cooksy.controller;

import com.cooksy.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
}
