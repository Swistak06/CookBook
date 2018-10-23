package com.swistak.CookBook.controller;

import com.swistak.CookBook.dto.RecipeDto;
import com.swistak.CookBook.model.Recipe;
import com.swistak.CookBook.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RecipeController {
    @Autowired
    RecipeService recipeService;

    @GetMapping("/recipe/{id}")
    public String showRecipePAge(@PathVariable("id") long id, Model model)
    {
        Recipe recipe = recipeService.findByID(id);
        if(recipe == null)
            return "redirect/";
        model.addAttribute("recipe", recipe);
        return "recipe-page";
    }

    @GetMapping("/addRecipe")
    public String showAddRecipePage(Model model){
        model.addAttribute("recipe",new RecipeDto());
        return "add-recipe";
    }

    @PostMapping("/addRecipe")
    public String saveRecipe(@Valid @ModelAttribute("recipe") RecipeDto recipeDto, BindingResult bindingResult){
        recipeService.createAndSaveRecipeFromDto(recipeDto);
        return "redirect:/";
    }

}
