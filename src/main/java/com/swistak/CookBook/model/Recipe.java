package com.swistak.CookBook.model;

import javax.persistence.*;

@Entity
@Table(name = "recipies")
public class Recipe {

    @Id
    @GeneratedValue
    @Column(name = "recipe_id",unique = true,nullable = false)
    private long id;

    private String name;
    private String formula;

    @Enumerated(EnumType.STRING)
    private RecipeCategory category;

    @Enumerated(EnumType.STRING)
    private RecipeDifficulty difficulty;

    @Column(name = "time")
    private int preparationTime;

    private int servings;









}
