package com.swistak.CookBook.repository;

import com.swistak.CookBook.model.Recipe;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe,Long>{
     Recipe findById(long id);

     @Query("select r from Recipe r order by r.addingDate desc")
     List<Recipe> findFiveNewestProducts();
     List<Recipe> findTop5ByAddingDateIsNotNullOrderByAddingDateDesc();
}
