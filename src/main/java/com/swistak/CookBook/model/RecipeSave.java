package com.swistak.CookBook.model;

import javax.persistence.*;

@Entity
@Table(name="recipe_saves")
public class RecipeSave {

    @Id
    @GeneratedValue
    @Column(name = "recipe_save_id")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="user_id")
    User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id")
    Recipe recipe;

    public RecipeSave() {
    }

    public RecipeSave(User user, Recipe recipe) {
        this.user = user;
        this.recipe = recipe;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
