package com.cooksy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/temp")
public class TemporaryController {

    @GetMapping("logout")
    public String getLogout() {
        return "fragments/logout-button";
    }

    @GetMapping("sidebar")
    public String getSidebar() {
        return "navbar";
    }

    @GetMapping("menu")
    public String getMenu() {
        return "menu";
    }

}
