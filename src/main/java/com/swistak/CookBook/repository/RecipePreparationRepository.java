package com.swistak.CookBook.repository;

import com.swistak.CookBook.model.RecipePreparation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipePreparationRepository extends JpaRepository<RecipePreparation,Long>{
    Long countByRecipeId(long id);
    RecipePreparation findByUserIdAndRecipeId(long user_id, long recipe_id);
}
