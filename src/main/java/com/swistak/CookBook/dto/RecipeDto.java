package com.swistak.CookBook.dto;

import com.swistak.CookBook.model.RecipeCategory;
import com.swistak.CookBook.model.RecipeDifficulty;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class RecipeDto {

    @NotBlank
    private String name;

    @NotBlank
    private String formula;

    @NotNull
    private RecipeCategory category;

    @NotNull
    private RecipeDifficulty difficulty;

    @NotNull
    @Range(min = 0,max = 1500)
    private int preparationTime;

    @NotNull
    @Range(min = 0,max = 1000)
    private int servings;

    public RecipeDto() {
    }

    public RecipeDto(@NotBlank String name, @NotBlank String formula, @NotNull RecipeCategory category, @NotNull RecipeDifficulty difficulty, @NotNull @Range(min = 0, max = 1500) int preparationTime, @NotNull @Range(min = 0, max = 1000) int servings) {
        this.name = name;
        this.formula = formula;
        this.category = category;
        this.difficulty = difficulty;
        this.preparationTime = preparationTime;
        this.servings = servings;
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

    public RecipeCategory getCategory() {
        return category;
    }

    public void setCategory(RecipeCategory category) {
        this.category = category;
    }

    public RecipeDifficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(RecipeDifficulty difficulty) {
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
}
