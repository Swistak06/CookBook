package com.swistak.CookBook.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "steps")
public class Step {

    @Id
    @GeneratedValue
    @Column(name = "step_id")
    private long id;

    @NotBlank
    private String formula;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    public Step() {
    }

    public Step(@NotBlank String formula, Recipe recipe) {
        this.formula = formula;
        this.recipe = recipe;
    }

    public Step(@NotBlank String formula) {
        this.formula = formula;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }
}
