package com.swistak.CookBook.controller;

import com.swistak.CookBook.model.Recipe;
import com.swistak.CookBook.model.RecipeRate;
import com.swistak.CookBook.model.User;
import com.swistak.CookBook.service.RecipeService;
import com.swistak.CookBook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class RecipeComponentsController {

    @Autowired
    RecipeService recipeService;

    @Autowired
    UserService userService;

    @GetMapping("/api/prepareRecipe/recipe{id}")
    @ResponseBody
    public long getRecipePreparationsNumber(@PathVariable("id") long id, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        Recipe recipe = recipeService.findByID(id);
        recipeService.addOrRemovePrepFromRecipe(recipe, user);
        return recipe.getPreparationsByUsers();
    }


    @PostMapping("/api/rateRecipe/recipe{id}")
    @ResponseBody
    public int getRecipeRate(@PathVariable("id") long id, Principal principal,@RequestParam("rateVal") int rateVal){
        System.out.println(rateVal);
        User user = userService.findByUsername(principal.getName());
        Recipe recipe = recipeService.findByID(id);
        recipeService.addOrChangeRecipeRate(recipe,user,rateVal);

        return rateVal;
    }

    @GetMapping("/api/getRecipeRateFromUser/recipe{id}")
    @ResponseBody
    public int getRecipeRateFromUser(@PathVariable("id") long id, Principal principal){
        Recipe recipe = recipeService.findByID(id);
        User user = userService.findByUsername(principal.getName());
        RecipeRate recipeRate = recipeService.findRecipeRateByRecipeAndUser(recipe,user);
        if(recipeRate == null)
            return 0;
        else
            return recipeRate.getRate();
    }
}
