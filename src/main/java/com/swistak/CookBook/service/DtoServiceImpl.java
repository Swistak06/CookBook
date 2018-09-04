package com.swistak.CookBook.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.swistak.CookBook.dto.IngredientDto;
import com.swistak.CookBook.model.Ingredient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class DtoServiceImpl implements DtoService{

    @Override
    public List<IngredientDto> convertJsonToList(String json) {
        List<IngredientDto> ingredientDtoList = null;
        try {
            IngredientDto[] specArray = new ObjectMapper().readValue(json, IngredientDto[].class);
            ingredientDtoList = Arrays.asList(specArray);
        }
        catch(Exception e){

        }
        return ingredientDtoList;
    }

//    @Override
//    public List<Ingredient> convertDtoToObject(List<IngredientDto> ingredientDtoList) {
//        List<Ingredient> ingredientList = new ArrayList<>();
//        for (IngredientDto i: ingredientDtoList
//             ) {
//            Ingredient ingredient = new Ingredient();
//            ingredient.setName(i.getName());
//            ingredient.setAmount(i.getValue());
//            ingredientList.add(ingredient);
//        }
//        return ingredientList;
//    }
}
