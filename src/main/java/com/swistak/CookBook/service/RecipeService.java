package com.swistak.CookBook.service;

import com.swistak.CookBook.dto.RecipeDto;
import com.swistak.CookBook.model.Recipe;

public interface RecipeService {
    Recipe createAndSaveRecipeFromDto(RecipeDto recipeDto);
    Recipe save(Recipe recipe);
    Recipe findByID(long id);

}
