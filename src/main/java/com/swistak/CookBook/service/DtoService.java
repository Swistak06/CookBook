package com.swistak.CookBook.service;

import com.swistak.CookBook.dto.ImageDto;
import com.swistak.CookBook.dto.IngredientDto;
import com.swistak.CookBook.dto.StepDto;
import com.swistak.CookBook.model.Ingredient;

import java.util.List;

public interface DtoService {
    List<IngredientDto> convertJsonIngredientList(String json);
//    List<Ingredient> convertDtoToObject(List<IngredientDto> ingredientDtoList);
    List<StepDto> convertJsonStepList(String json);
    List<ImageDto> convertJsonImageList(String json);
}
