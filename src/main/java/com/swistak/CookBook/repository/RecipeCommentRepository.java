package com.swistak.CookBook.repository;

import com.swistak.CookBook.model.RecipeComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeCommentRepository extends JpaRepository<RecipeComment,Long>{
}
