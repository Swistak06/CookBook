package com.swistak.CookBook.controller;

import com.swistak.CookBook.dto.RecipeDto;
import com.swistak.CookBook.model.Recipe;
import com.swistak.CookBook.model.RecipeComment;
import com.swistak.CookBook.model.User;
import com.swistak.CookBook.service.RecipeService;
import com.swistak.CookBook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.RoundingMode;
import java.security.Principal;
import java.text.DecimalFormat;

@Controller
public class RecipeController {
    @Autowired
    RecipeService recipeService;

    @Autowired
    UserService userService;

    @GetMapping("/recipe/{id}")
    public String showRecipePage(@PathVariable("id") long id, Model model, Principal principal)
    {
        Recipe recipe = recipeService.findByID(id);
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.HALF_UP);
        RecipeComment recipeComment = new RecipeComment(userService.findByUsername(principal.getName()), recipe);
        if(recipe == null)
            return "redirect/";
        model.addAttribute("recipe", recipe);
        model.addAttribute("avgRate", df.format(recipe.getAverageRate()));
        model.addAttribute("recipeComment", recipeComment);
        return "recipe-page";
    }

    @GetMapping("/addRecipe")
    public String showAddRecipePage(Model model){
        model.addAttribute("recipe",new RecipeDto());
        return "add-recipe";
    }

    @PostMapping("/addRecipe")
    public String saveRecipe(@Valid @ModelAttribute("recipe") RecipeDto recipeDto, BindingResult bindingResult,Principal principal){
        User owner = userService.findByUsername(principal.getName());
        recipeService.createAndSaveRecipeFromDto(recipeDto,owner);
        return "redirect:/";
    }

    @PostMapping("/prepareRecipe/{id}")
    public String addPreparationToRecipe(@PathVariable("id") long id, Principal principal, @RequestHeader("Referer") String referer){
        User user = userService.findByUsername(principal.getName());
        Recipe recipe = recipeService.findByID(id);
        recipeService.addOrRemovePrepFromRecipe(recipe,user);
        return "redirect:" + referer;
    }
}
