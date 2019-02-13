package com.swistak.CookBook.controller;

import com.swistak.CookBook.model.Recipe;
import com.swistak.CookBook.model.RecipeComment;
import com.swistak.CookBook.model.RecipeRate;
import com.swistak.CookBook.model.User;
import com.swistak.CookBook.service.RecipeService;
import com.swistak.CookBook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.RoundingMode;
import java.security.Principal;
import java.text.DecimalFormat;

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
    public String getRecipeRate(@PathVariable("id") long id, Principal principal,@RequestParam("rateVal") int rateVal){
        User user = userService.findByUsername(principal.getName());
        Recipe recipe = recipeService.findByID(id);
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.HALF_UP);
        recipeService.addOrChangeRecipeRate(recipe,user,rateVal);
        return df.format(recipe.getAverageRate());
    }

    @GetMapping("/api/getRecipeRateFromUser/recipe{id}")
    @ResponseBody
    public int getRecipeRateFromUser(@PathVariable("id") long id, Principal principal){
        Recipe recipe = recipeService.findByID(id);
        if(principal == null)
            return 0;
        User user = userService.findByUsername(principal.getName());
        RecipeRate recipeRate = recipeService.findRecipeRateByRecipeAndUser(recipe,user);
        if(recipeRate == null)
            return 0;
        else
            return recipeRate.getRate();
    }

    @PostMapping("/api/addComment")
    public String addCommentToRecipe(@ModelAttribute("recipeComment") RecipeComment recipeComment ){
        if(!recipeComment.getCommentText().equals(""))
            recipeService.addCommentToRecipe(recipeComment);
        return "redirect:/recipe/" + recipeComment.getCommentedRecipe().getId();
    }
}
