package com.swistak.CookBook.controller;

import com.swistak.CookBook.model.Recipe;
import com.swistak.CookBook.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    RecipeService recipeService;

    @GetMapping("/")
    public String showHomePage(Model model){
        List<Recipe> newestRecipes = recipeService.findNewestRecipes();
        List<Recipe> mostPopularRecipes = recipeService.findBestRatedRecipes();

        model.addAttribute("newestRec",newestRecipes);
        model.addAttribute("bestRated",mostPopularRecipes);
        return "index";
    }
}
