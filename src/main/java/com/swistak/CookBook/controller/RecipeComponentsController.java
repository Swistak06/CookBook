package com.swistak.CookBook.controller;

import com.swistak.CookBook.model.Recipe;
import com.swistak.CookBook.model.User;
import com.swistak.CookBook.service.RecipeService;
import com.swistak.CookBook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Controller
public class RecipeComponentsController {

    @Autowired
    RecipeService recipeService;

    @Autowired
    UserService userService;


    @GetMapping("/api/likeRecipe/recipe{id}")
    @ResponseBody
    public long getRecipeLikesNumber(@PathVariable("id") long id){
        Recipe recipe = recipeService.findByID(id);
        return recipe.getLikes();
    }

    @GetMapping("/api/prepareRecipe/recipe{id}")
    @ResponseBody
    public long getRecipePreparationsNumber(@PathVariable("id") long id, Principal principal){
        User user = userService.findByUsername(principal.getName());
        Recipe recipe = recipeService.findByID(id);
        recipeService.addOrRemovePrepFromRecipe(recipe,user);
        return recipe.getPreparationsByUsers();
    }
}
