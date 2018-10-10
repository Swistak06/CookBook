package com.swistak.CookBook.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.swistak.CookBook.dto.ImageDto;
import com.swistak.CookBook.dto.IngredientDto;
import com.swistak.CookBook.dto.StepDto;
import com.swistak.CookBook.model.Ingredient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class DtoServiceImpl implements DtoService{

    @Override
    public List<IngredientDto> convertJsonIngredientList(String json) {
        List<IngredientDto> ingredientDtoList = null;
        try {
            IngredientDto[] ingredientDtoArray = new ObjectMapper().readValue(json, IngredientDto[].class);
            ingredientDtoList = Arrays.asList(ingredientDtoArray);
        }
        catch(Exception e){

        }
        return ingredientDtoList;
    }

    @Override
    public List<StepDto> convertJsonStepList(String json) {
        List<StepDto> stepDtoList = null;
        try{
            StepDto[] stepDtoArray = new ObjectMapper().readValue(json, StepDto[].class);
            stepDtoList = Arrays.asList(stepDtoArray);
        }
        catch(Exception e){

        }
        return stepDtoList;
    }

    @Override
    public List<ImageDto> convertJsonImageList(String json) {
        List<ImageDto> imageDtoList = null;
        try{
            ImageDto[] imageDtoArray = new ObjectMapper().readValue(json, ImageDto[].class);
            imageDtoList = Arrays.asList(imageDtoArray);
        }
        catch(Exception e){

        }
        return imageDtoList;
    }
}
