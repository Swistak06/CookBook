package com.swistak.CookBook.controller;

import com.swistak.CookBook.model.Recipe;
import com.swistak.CookBook.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ProfileController {

    final private RecipeService recipeService;

    @Autowired
    public ProfileController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }


    @GetMapping("/profile/myCookBook/{category}/{pageNum}")
    public String showMyCookBookCategoryPage(@PathVariable("category") String category, @PathVariable("pageNum") int pageNum, Model model, Principal principal){
        List<Recipe> recipesFound = new ArrayList<>();
        List<Recipe> nextPageRecipes = new ArrayList<>();
        boolean isNextPageEmpty = true;
        int pageNumber = pageNum;
        int numberOfCardGroups = 1;
        if(category.equals("maindish"))
            category="main dish";
        recipesFound = recipeService.getSavedRecipesByUserFromCategoryInRange(principal.getName(),category,(pageNum-1)*16,16);
        nextPageRecipes = recipeService.getSavedRecipesByUserFromCategoryInRange(principal.getName(),category,pageNum*16,16);
        if(!nextPageRecipes.isEmpty())
            isNextPageEmpty = false;
        numberOfCardGroups = (int)Math.ceil(recipesFound.size()/4.0);
        Recipe[][] dividedRecipes = new Recipe[numberOfCardGroups][4];
        for(int i = 0; i < recipesFound.size(); i++)
            dividedRecipes[i/4][i%4] = recipesFound.get(i);
        if(category.equals("all"))
            category = "All Recipes";
        model.addAttribute("chosenCategory","Saved " + category + "s");
        model.addAttribute("recipesFound",dividedRecipes);
        model.addAttribute("isNextPageEmpty", isNextPageEmpty);
        model.addAttribute("pageNumber", pageNumber);


        return "mycookbook-page";
    }


}
