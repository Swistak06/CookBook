package com.swistak.CookBook.model;

import javax.persistence.*;
import java.util.HashSet;
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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Ingredient> ingredients = new HashSet<>();

    @OneToMany(mappedBy = "recipe" ,cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Step> steps = new HashSet<>();



}
