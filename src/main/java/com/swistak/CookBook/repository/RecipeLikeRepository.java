package com.swistak.CookBook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeLikeRepository extends JpaRepository<RecipeLike,Long> {
    Long countByRecipeId(long id);
    RecipeLike findByUserIdAndRecipeId(long user_id, long recipe_id);
}
