package com.swistak.CookBook.repository;

import com.swistak.CookBook.model.Recipe;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe,Long>{
     Recipe findById(long id);
     
     List<Recipe> findTop4ByAddingDateIsNotNullOrderByAddingDateDesc();
     List<Recipe> findTop4ByAverageRateIsNotNullOrderByAverageRateDesc();

     //String sql = "Select r from Recipe r where r.category like :category order by r.id desc";

}
