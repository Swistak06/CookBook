package com.swistak.CookBook.repository;

import com.swistak.CookBook.model.Recipe;
import com.swistak.CookBook.model.RecipeRate;
import com.swistak.CookBook.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRateRepository extends JpaRepository<RecipeRate, Long> {
    RecipeRate findByRecipeAndUser(Recipe recipe, User user);
}
