package com.swistak.CookBook.controller;

import com.swistak.CookBook.dto.RecipeDto;
import com.swistak.CookBook.model.Recipe;
import com.swistak.CookBook.model.User;
import com.swistak.CookBook.service.RecipeService;
import com.swistak.CookBook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class RecipeController {
    @Autowired
    RecipeService recipeService;

    @Autowired
    UserService userService;

    @GetMapping("/recipe/{id}")
    public String showRecipePAge(@PathVariable("id") long id, Model model)
    {
        Recipe recipe = recipeService.findByID(id);
        long preparations = recipeService.countNumberOfPreparations(recipe);
        if(recipe == null)
            return "redirect/";
        model.addAttribute("recipe", recipe);
        model.addAttribute("preparations",preparations);

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

}
