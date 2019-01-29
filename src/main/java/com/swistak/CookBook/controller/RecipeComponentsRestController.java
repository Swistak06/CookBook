package com.swistak.CookBook.controller;

import com.swistak.CookBook.model.Recipe;
import com.swistak.CookBook.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecipeComponentsRestController {

    @Autowired
    RecipeService recipeService;


    @GetMapping("/api/likeRecipe/recipe{id}")
    @ResponseBody
    public long getRecipeLikesNumber(@PathVariable("id") long id){
        Recipe recipe = recipeService.findByID(id);
        return recipeService.countLikesOfRecipe(recipe);
    }
}
