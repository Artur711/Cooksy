package com.cooksy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/temp")
public class TemporaryController {

    @GetMapping("/logout")
    public String getLogout() {
        return "fragments/logout-button";
    }

    @GetMapping("/navbar")
    public String getSidebar() {
        return "fragments/navbar";
    }

    @GetMapping("/menu")
    public String getMenu() {
        return "menu";
    }

    @GetMapping("/footer")
    public String getFooter() {
        return "fragments/footer";
    }

    @GetMapping("/logo")
    public String getLogo() {
        return "fragments/logo";
    }

    @GetMapping("/pag")
    public String getPagination() {
        return "fragments/pagination";
    }

    @GetMapping("/favorites")
    public String getFavorites() {
        return "favorites";
    }

    @GetMapping("/shopping-list")
    public String getShoppingList(){return "shopping-list";}

}
