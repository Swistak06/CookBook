package com.swistak.CookBook.model;


import org.hibernate.validator.constraints.Range;

import javax.persistence.*;

@Entity
@Table(name = "recipe_rates")
public class RecipeRate {

    @Id
    @GeneratedValue
    @Column(name = "recipe_rate_id")
    private long id;

    @Range(min=0, max=5)
    private double rate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public RecipeRate() {
    }
    public RecipeRate(@Range(min = 0, max = 5) double rate, Recipe recipe, User user) {
        this.rate = rate;
        this.recipe = recipe;
        this.user = user;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public double getRate() {
        return rate;
    }
    public void setRate(double rate) {
        this.rate = rate;
    }
    public Recipe getRecipe() {
        return recipe;
    }
    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
}
