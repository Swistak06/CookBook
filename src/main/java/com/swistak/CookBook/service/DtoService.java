package com.swistak.CookBook.service;

import com.swistak.CookBook.dto.IngredientDto;
import com.swistak.CookBook.model.Ingredient;

import java.util.List;

public interface DtoService {
    List<IngredientDto> convertJsonToList(String json);
//    List<Ingredient> convertDtoToObject(List<IngredientDto> ingredientDtoList);
}
