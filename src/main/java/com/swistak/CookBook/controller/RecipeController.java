package com.swistak.CookBook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RecipeController {

    @GetMapping("/addRecipe")
    public String showAddRecipePage(){
        return "addRecipe";
    }
}
