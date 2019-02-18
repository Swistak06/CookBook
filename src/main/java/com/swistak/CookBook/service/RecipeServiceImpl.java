package com.swistak.CookBook.service;

import com.swistak.CookBook.dto.ImageDto;
import com.swistak.CookBook.dto.IngredientDto;
import com.swistak.CookBook.dto.RecipeDto;
import com.swistak.CookBook.dto.StepDto;
import com.swistak.CookBook.model.*;
import com.swistak.CookBook.repository.RecipeCommentRepository;
import com.swistak.CookBook.repository.RecipePreparationRepository;
import com.swistak.CookBook.repository.RecipeRateRepository;
import com.swistak.CookBook.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class RecipeServiceImpl implements RecipeService{

    private RecipeRepository recipeRepository;
    private RecipePreparationRepository recipePreparationRepository;
    private RecipeRateRepository recipeRateRepository;
    private RecipeCommentRepository recipeCommentRepository;
    private EntityManager entityManager;

    private DtoService dtoService;

    @Autowired
    public RecipeServiceImpl(DtoService dtoService, RecipeRepository recipeRepository, RecipePreparationRepository recipePreparationRepository, RecipeRateRepository recipeRateRepository, RecipeCommentRepository recipeCommentRepository, EntityManager entityManager) {
        this.dtoService = dtoService;
        this.recipeRepository = recipeRepository;
        this.recipePreparationRepository = recipePreparationRepository;
        this.recipeRateRepository = recipeRateRepository;
        this.recipeCommentRepository = recipeCommentRepository;
        this.entityManager = entityManager;
    }

    @Override
    public Recipe createAndSaveRecipeFromDto(RecipeDto recipeDto, User owner) {

        Recipe recipe = new Recipe(recipeDto.getName(),recipeDto.getDescription(),recipeDto.getCategory(),recipeDto.getDifficulty(),
                recipeDto.getPreparationTime(), recipeDto.getServings(), owner);


        List<IngredientDto> ingredientDtoList = dtoService.convertJsonIngredientList(recipeDto.getJsonIngredientList());
        for (IngredientDto i : ingredientDtoList) {
            recipe.getIngredients().add(new Ingredient(i.getName(),i.getValue(),recipe));
        }

        List<StepDto> stepDtoList = dtoService.convertJsonStepList(recipeDto.getJsonStepsList());
        for (StepDto step : stepDtoList) {
            recipe.getSteps().add(new Step(step.getValue(),stepDtoList.indexOf(step)+1,recipe));
        }

        List<ImageDto> imageDtoList = dtoService.convertJsonImageList(recipeDto.getJsonImagesList());
        if(imageDtoList != null ){
            for (ImageDto image : imageDtoList) {
                recipe.getImages().add(new Image(image.getValue(),recipe));
            }
        }
        recipe.setAddingDate(Calendar.getInstance());
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
    public void addOrRemovePrepFromRecipe(Recipe recipe, User user) {
        RecipePreparation recipePreparation = recipePreparationRepository.findByUserIdAndRecipeId(user.getId(),recipe.getId());
        if(recipePreparation == null){
            recipePreparationRepository.save(new RecipePreparation(user,recipe));
            recipe.addOnePreparation();
            recipeRepository.save(recipe);
        }
        else{
            recipePreparationRepository.delete(recipePreparation);
            recipe.removeOnePreparation();
            recipeRepository.save(recipe);
        }
    }

    @Override
    public void addOrChangeRecipeRate(Recipe recipe, User user, int rate) {
        RecipeRate recipeRate = recipeRateRepository.findByRecipeAndUser(recipe,user);
        if(recipeRate == null){
            recipeRateRepository.save(new RecipeRate(rate,recipe,user));
            addOneRecipeRate(recipe, rate);
        }
        else{
            updateRecipeRate(recipe,recipeRate.getRate(),rate);
            recipeRate.setRate(rate);
            recipeRateRepository.save(recipeRate);
        }
    }

    @Override
    public RecipeRate findRecipeRateByRecipeAndUser(Recipe recipe, User user) {
        return recipeRateRepository.findByRecipeAndUser(recipe,user);
    }

    @Override
    public List<Recipe> findNewestRecipes() {
        return recipeRepository.findTop4ByAddingDateIsNotNullOrderByAddingDateDesc();
    }

    @Override
    public List<Recipe> findBestRatedRecipes() {
        return recipeRepository.findTop4ByAverageRateIsNotNullOrderByAverageRateDesc();
    }

    @Override
    public List<Recipe> getRecipesFromCategoryInRange(String category, int start, int stop) {
        String sql = "Select r from Recipe r where r.category like :category order by r.averageRate desc";
        List<Recipe> result = new ArrayList<>();

        result.addAll(entityManager.createQuery(sql).setFirstResult(start).setMaxResults(stop).setParameter("category",category).getResultList());

        return result;
    }

    @Override
    public List<Recipe> getRecipesByNameFromCategoryInRange(String recipeName, String category, int start, int stop) {
        String sql = "Select r from Recipe r where r.category like :category and r.name like CONCAT('%',:recipeName,'%') order by r.id desc";
        List<Recipe> result = new ArrayList<>();

        result.addAll(entityManager.createQuery(sql).setFirstResult(start).setMaxResults(stop).setParameter("category",category).setParameter("recipeName",recipeName).getResultList());

        return result;
    }

    @Override
    public List<Recipe> getRecipesByNameInRange(String recipeName, int start, int stop) {
        String sql = "Select r from Recipe r where r.name like CONCAT('%',:recipeName,'%') order by r.id desc";
        List<Recipe> result = new ArrayList<>();

        result.addAll(entityManager.createQuery(sql).setFirstResult(start).setMaxResults(stop).setParameter("recipeName",recipeName).getResultList());

        return result;
    }

    @Override
    public RecipeComment addCommentToRecipe(RecipeComment recipeComment) {
        return recipeCommentRepository.save(recipeComment);
    }

    private void addOneRecipeRate(Recipe recipe, int rate){
        double avg = recipe.getNumberOfRates() * recipe.getAverageRate();
        avg += rate;
        recipe.incrementNumberOfRates();
        avg /= recipe.getNumberOfRates();
        recipe.setAverageRate(avg);
        recipeRepository.save(recipe);
    }
    private void updateRecipeRate(Recipe recipe,int oldRate, int newRate){
        double avg = recipe.getNumberOfRates() * recipe.getAverageRate();
        avg -= oldRate;
        avg += newRate;
        avg /= recipe.getNumberOfRates();
        recipe.setAverageRate(avg);
    }

}
