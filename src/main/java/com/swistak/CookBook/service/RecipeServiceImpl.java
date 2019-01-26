package com.swistak.CookBook.service;

import com.swistak.CookBook.dto.ImageDto;
import com.swistak.CookBook.dto.IngredientDto;
import com.swistak.CookBook.dto.RecipeDto;
import com.swistak.CookBook.dto.StepDto;
import com.swistak.CookBook.model.*;
import com.swistak.CookBook.repository.RecipePreparationRepository;
import com.swistak.CookBook.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeServiceImpl implements RecipeService{

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    RecipePreparationRepository recipePreparationRepository;

    private DtoService dtoService;

    public RecipeServiceImpl(DtoService dtoService) {
        this.dtoService = dtoService;
    }

    @Override
    public Recipe createAndSaveRecipeFromDto(RecipeDto recipeDto, User owner) {

        Recipe recipe = new Recipe(recipeDto.getName(),recipeDto.getDescription(),recipeDto.getCategory(),recipeDto.getDifficulty(),
                recipeDto.getPreparationTime(), recipeDto.getServings(), owner);

        List<IngredientDto> ingredientDtoList = dtoService.convertJsonIngredientList(recipeDto.getJsonIngredientList());
        for (IngredientDto i : ingredientDtoList
             ) {
            recipe.getIngredients().add(new Ingredient(i.getName(),i.getValue(),recipe));
        }

        List<StepDto> stepDtoList = dtoService.convertJsonStepList(recipeDto.getJsonStepsList());
        for (StepDto step : stepDtoList
                ) {
            recipe.getSteps().add(new Step(step.getValue(),recipe));
        }

        List<ImageDto> imageDtoList = dtoService.convertJsonImageList(recipeDto.getJsonImagesList());
        if(imageDtoList != null ){
            for (ImageDto image : imageDtoList
                    ) {
                recipe.getImages().add(new Image(image.getValue(),recipe));
            }
        }

        return save(recipe);
    }

    @Override
    public Recipe save(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    @Override
    public Recipe findByID(long id) {
        return recipeRepository.findById(id);
    }

    @Override
    public long countNumberOfPreparations(Recipe recipe) {
        return recipePreparationRepository.countByRecipeId(recipe.getId());
    }
}
