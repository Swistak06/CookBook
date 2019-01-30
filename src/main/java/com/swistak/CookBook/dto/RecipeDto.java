package com.swistak.CookBook.dto;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class RecipeDto {

    @NotBlank
    private String name;

    @NotBlank
    private String formula;

    @NotBlank
    private String category;

    @NotNull
    private String difficulty;

    @NotNull
    @Range(min = 0,max = 1500)
    private int preparationTime;

    @NotNull
    @Range(min = 0,max = 1000)
    private int servings;

    @NotBlank
    private String jsonIngredientList;

    @NotBlank
    private String description;

    @NotBlank
    private String jsonStepsList;

    private String jsonImagesList;

    public RecipeDto() {
    }

    public RecipeDto(@NotBlank String name, @NotBlank String formula, @NotNull String category, @NotNull String difficulty, @NotNull @Range(min = 0, max = 1500) int preparationTime, @NotNull @Range(min = 0, max = 1000) int servings, @NotBlank String jsonIngredientList, @NotBlank String description, @NotBlank String jsonStepsList, String jsonImagesList) {
        this.name = name;
        this.formula = formula;
        this.category = category;
        this.difficulty = difficulty;
        this.preparationTime = preparationTime;
        this.servings = servings;
        this.jsonIngredientList = jsonIngredientList;
        this.description = description;
        this.jsonStepsList = jsonStepsList;
        this.jsonImagesList = jsonImagesList;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getFormula() {
        return formula;
    }
    public void setFormula(String formula) {
        this.formula = formula;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public String getDifficulty() {
        return difficulty;
    }
    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
    public int getPreparationTime() {
        return preparationTime;
    }
    public void setPreparationTime(int preparationTime) {
        this.preparationTime = preparationTime;
    }
    public int getServings() {
        return servings;
    }
    public void setServings(int servings) {
        this.servings = servings;
    }
    public String getJsonIngredientList() {
        return jsonIngredientList;
    }
    public void setJsonIngredientList(String jsonIngredientList) {
        this.jsonIngredientList = jsonIngredientList;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getJsonStepsList() {
        return jsonStepsList;
    }
    public void setJsonStepsList(String jsonStepsList) {
        this.jsonStepsList = jsonStepsList;
    }
    public String getJsonImagesList() {
        return jsonImagesList;
    }
    public void setJsonImagesList(String jsonImagesList) {
        this.jsonImagesList = jsonImagesList;
    }
}
