package com.swistak.CookBook.controller;

import com.swistak.CookBook.dto.RecipeDto;
import com.swistak.CookBook.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RecipeController {
    @Autowired
    RecipeService recipeService;

    @GetMapping("/addRecipe")
    public String showAddRecipePage(Model model){
        model.addAttribute("recipe",new RecipeDto());
        return "add-recipe";
    }

    @PostMapping("/addRecipe")
    public String saveRecipe(@Valid @ModelAttribute("recipe") RecipeDto recipeDto, BindingResult bindingResult){
        System.out.println(recipeDto.getName());
        System.out.println(recipeDto.getJsonImagesList());
        recipeService.createAndSaveRecipeFromDto(recipeDto);
        return "redirect:index";
    }

}
