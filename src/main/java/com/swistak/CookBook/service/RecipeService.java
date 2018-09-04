package com.swistak.CookBook.service;

import com.swistak.CookBook.dto.RecipeDto;
import com.swistak.CookBook.model.Recipe;

public interface RecipeService {
    Recipe saveRecipe(RecipeDto recipeDto);
    Recipe save(Recipe recipe);

}
