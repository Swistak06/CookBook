package com.swistak.CookBook.repository;

import com.swistak.CookBook.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe,Long>{
     Recipe findById(long id);
}
