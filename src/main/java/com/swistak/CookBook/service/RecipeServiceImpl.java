package com.swistak.CookBook.service;

import com.swistak.CookBook.dto.IngredientDto;
import com.swistak.CookBook.dto.RecipeDto;
import com.swistak.CookBook.model.Ingredient;
import com.swistak.CookBook.model.Recipe;
import com.swistak.CookBook.model.User;
import com.swistak.CookBook.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeServiceImpl implements RecipeService{

    @Autowired
    RecipeRepository recipeRepository;

    private DtoService dtoService;

    public RecipeServiceImpl(DtoService dtoService) {
        this.dtoService = dtoService;
    }

    @Override
    public Recipe saveRecipe(RecipeDto recipeDto) {

        Recipe recipe = new Recipe(recipeDto.getName(),recipeDto.getDescription(),recipeDto.getCategory(),recipeDto.getDifficulty(),
                recipeDto.getPreparationTime(), recipeDto.getServings(),new User());
        List<IngredientDto> ingredientDtoList = dtoService.convertJsonToList(recipeDto.getJsonIngredientList());
//        List<Ingredient> ingredientList = dtoService.convertDtoToObject(ingredientDtoList);
        for (IngredientDto i : ingredientDtoList
             ) {
            recipe.getIngredients().add(new Ingredient(i.getName(),i.getValue(),recipe));
        }
        return save(recipe);
    }

    @Override
    public Recipe save(Recipe recipe) {
        return recipeRepository.save(recipe);
    }
}
