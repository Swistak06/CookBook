package com.swistak.CookBook.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "recipes")
public class Recipe {

    @Id
    @GeneratedValue
    @Column(name = "recipe_id",unique = true,nullable = false)
    private long id;

    private String name;
    private String description;

    @Enumerated(EnumType.STRING)
    private RecipeCategory category;

    @Enumerated(EnumType.STRING)
    private RecipeDifficulty difficulty;

    @Column(name = "time")
    private int preparationTime;

    private int servings;

    private long likes = 0;
    private long preparationsByUsers = 0;

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
    private Set<RecipePreparation> recipeLikes = new HashSet<>();

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<RecipePreparation> recipePreparations = new HashSet<>();

    public Recipe() {
    }

    public Recipe(String name, String description, RecipeCategory category, RecipeDifficulty difficulty, int preparationTime, int servings, User user) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.difficulty = difficulty;
        this.preparationTime = preparationTime;
        this.servings = servings;
        this.user = user;
    }

    public Recipe(String name, String description, RecipeCategory category, RecipeDifficulty difficulty, int preparationTime, int servings, long likes, long preparationsByUsers, User user) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.difficulty = difficulty;
        this.preparationTime = preparationTime;
        this.servings = servings;
        this.likes = likes;
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

    public long getLikes() {
        return likes;
    }

    public void setLikes(long likes) {
        this.likes = likes;
    }

    public long getPreparationsByUsers() {
        return preparationsByUsers;
    }

    public void setPreparationsByUsers(long preparationsByUsers) {
        this.preparationsByUsers = preparationsByUsers;
    }

    public Set<RecipePreparation> getRecipeLikes() {
        return recipeLikes;
    }

    public void setRecipeLikes(Set<RecipePreparation> recipeLikes) {
        this.recipeLikes = recipeLikes;
    }

    public void addOneLike(){
        likes++;
    }
    public void removeOneLike(){
        likes--;
    }
    public void addOnePreparation(){
        preparationsByUsers++;
    }
    public void removeOnePreparation(){
        preparationsByUsers--;
    }
}
