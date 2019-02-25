package com.swistak.CookBook.repository;

import com.swistak.CookBook.model.Recipe;
import com.swistak.CookBook.model.RecipeSave;
import com.swistak.CookBook.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeSaveRepository extends JpaRepository<RecipeSave, Long> {
    RecipeSave findRecipeSaveByRecipeAndUser(Recipe recipe, User user);
}
