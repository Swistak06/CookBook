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
import java.util.List;

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
        RecipeComment recipeComment;
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.HALF_UP);
        if(principal != null){
            recipeComment = new RecipeComment(userService.findByUsername(principal.getName()), recipe);
        }
        else
            recipeComment = new RecipeComment();
        model.addAttribute("recipeComment", recipeComment);
        if(recipe == null)
            return "redirect/";
        model.addAttribute("recipe", recipe);
        model.addAttribute("avgRate", df.format(recipe.getAverageRate()));

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

    @GetMapping("/recipes/{category}/{page}")
    public String showPageWithRecipesFromCategory(@PathVariable("category") String category, @PathVariable("page") int page, Model model){
        boolean isNextPageEmpty = true;
        if(category.equals("maindish"))
            category = "Main dish";
        List<Recipe> recipes = recipeService.getRecipesFromCategoryInRange(category,(page-1)*10,10);
        List<Recipe> nextPage = recipeService.getRecipesFromCategoryInRange(category,page*10,10);
        if(nextPage.size() > 0 )
            isNextPageEmpty = false;
        model.addAttribute("isNextPageEmpty", isNextPageEmpty);
        model.addAttribute("recipesFound", recipes);
        model.addAttribute("pageNumber", page);
        return "searching-result-page";
    }

    @PostMapping("/searchRecipe/{pageNum}")
    public String searchForRecipe(@RequestParam("searchedRecipeName") String recipeName, @RequestParam("searchedCategory") String category, @PathVariable("pageNum") int page, Model model){
        boolean isNextPageEmpty = true;
        List<Recipe> recipes;
        List<Recipe> nextPage;
        if(category.equals("All")){
            recipes = recipeService.getRecipesByNameInRange(recipeName,(page-1)*10,10);
            nextPage = recipeService.getRecipesByNameInRange(recipeName,page*10,10);
        }
        else {
            recipes = recipeService.getRecipesByNameFromCategoryInRange(recipeName,category,(page-1)*10,10);
            nextPage = recipeService.getRecipesByNameFromCategoryInRange(recipeName,category,page*10,10);
        }
        if(nextPage.size() > 0 )
            isNextPageEmpty = false;
        model.addAttribute("isNextPageEmpty", isNextPageEmpty);
        model.addAttribute("recipesFound", recipes);
        model.addAttribute("pageNumber", page);
        return "searching-result-page";
    }
}
