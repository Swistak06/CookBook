package com.swistak.CookBook.repository;

import com.swistak.CookBook.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe,Long>{

}
