package com.swistak.CookBook.service;

import com.swistak.CookBook.dto.RecipeDto;
import com.swistak.CookBook.model.Recipe;
import com.swistak.CookBook.model.RecipeComment;
import com.swistak.CookBook.model.RecipeRate;
import com.swistak.CookBook.model.User;

import java.util.List;

public interface RecipeService {
    Recipe createAndSaveRecipeFromDto(RecipeDto recipeDto, User user);
    Recipe save(Recipe recipe);
    Recipe findByID(long id);
    void addOrRemovePrepFromRecipe(Recipe recipe, User user);
    void addOrChangeRecipeRate(Recipe recipe, User user, int rate);
    RecipeRate findRecipeRateByRecipeAndUser(Recipe recipe, User user);
    List<Recipe> findNewestRecipes();
    List<Recipe> findBestRatedRecipes();
    List<Recipe> findRecipesByCategory(String category);
    RecipeComment addCommentToRecipe(RecipeComment recipeComment);
}
