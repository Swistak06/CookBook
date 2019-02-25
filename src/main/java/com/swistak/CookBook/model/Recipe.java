package com.swistak.CookBook.model;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "recipes")
public class Recipe {

    @Id
    @GeneratedValue
    @Column(name = "recipe_id",unique = true,nullable = false)
    private long id;

    private String name;
    private String description;

    private String category;

    private String difficulty;

    @Column(name = "time")
    private int preparationTime;

    private int servings;

    private long preparationsByUsers = 0;

    private Calendar addingDate;
    private double averageRate = 0.0;
    private int numberOfRates = 0;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Ingredient> ingredients = new ArrayList<>();

    @OneToMany(mappedBy = "recipe" ,cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Step> steps = new ArrayList<>();

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Image> images = new ArrayList<>();

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<RecipeRate> recipeRates = new HashSet<>();

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<RecipePreparation> recipePreparations = new HashSet<>();

    @OneToMany(mappedBy = "commentedRecipe", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<RecipeComment> recipeComments = new ArrayList<>();

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<RecipeSave> recipeSaves = new HashSet<>();

    public Recipe() {
    }

    public Recipe(String name, String description, String category, String difficulty, int preparationTime, int servings, User user) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.difficulty = difficulty;
        this.preparationTime = preparationTime;
        this.servings = servings;
        this.user = user;
    }

    public Recipe(String name, String description, String category, String difficulty, int preparationTime, int servings, long preparationsByUsers, User user) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.difficulty = difficulty;
        this.preparationTime = preparationTime;
        this.servings = servings;
        this.preparationsByUsers = preparationsByUsers;
        this.user = user;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
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
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public List<Ingredient> getIngredients() {
        return ingredients;
    }
    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
    public List<Step> getSteps() {
        return steps;
    }
    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }
    public List<Image> getImages() {
        return images;
    }
    public void setImages(List<Image> images) {
        this.images = images;
    }
    public Set<RecipeRate> getRecipeRates() {
        return recipeRates;
    }
    public void setRecipeRates(Set<RecipeRate> recipeRates) {
        this.recipeRates = recipeRates;
    }
    public Set<RecipePreparation> getRecipePreparations() {
        return recipePreparations;
    }
    public void setRecipePreparations(Set<RecipePreparation> recipePreparations) {
        this.recipePreparations = recipePreparations;
    }
    public long getPreparationsByUsers() {
        return preparationsByUsers;
    }
    public void setPreparationsByUsers(long preparationsByUsers) {
        this.preparationsByUsers = preparationsByUsers;
    }
    public Calendar getAddingDate() {
        return addingDate;
    }
    public void setAddingDate(Calendar addingDate) {
        this.addingDate = addingDate;
    }
    public double getAverageRate() {
        return averageRate;
    }
    public void setAverageRate(double averageRate) {
        this.averageRate = averageRate;
    }
    public int getNumberOfRates() {
        return numberOfRates;
    }
    public void setNumberOfRates(int numberOfRates) {
        this.numberOfRates = numberOfRates;
    }
    public List<RecipeComment> getRecipeComments() {
        return recipeComments;
    }
    public void setRecipeComments(List<RecipeComment> recipeComments) {
        this.recipeComments = recipeComments;
    }

    public void incrementNumberOfRates(){
        numberOfRates++;
    }
    public void addOnePreparation(){
        preparationsByUsers++;
    }
    public void removeOnePreparation(){
        preparationsByUsers--;
    }

}
