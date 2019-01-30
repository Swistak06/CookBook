package com.swistak.CookBook.service;

import com.swistak.CookBook.dto.RecipeDto;
import com.swistak.CookBook.model.Recipe;
import com.swistak.CookBook.model.User;

public interface RecipeService {
    Recipe createAndSaveRecipeFromDto(RecipeDto recipeDto, User user);
    Recipe save(Recipe recipe);
    Recipe findByID(long id);
    void addOrRemoveLikeFromRecipe(Recipe recipe, User user);
    void addOrRemovePrepFromRecipe(Recipe recipe, User user);

}
