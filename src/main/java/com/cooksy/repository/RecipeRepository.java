package com.cooksy.repository;

import com.cooksy.model.Recipe;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {

    @Transactional
    @Modifying
    @Query(value = "delete from recipe_composition where recipe_id = :recipeId", nativeQuery = true)
    void deleteRecipeCompositionByRecipeId(@Param("recipeId") Long recipeId);

    @Transactional
    @Modifying
    @Query(value = "delete from recipes where recipe_id = :recipeId", nativeQuery = true)
    void deleteRecipeByRecipeId(@Param("recipeId") Long recipeId);
}
