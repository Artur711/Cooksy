package com.cooksy.repository;

import com.cooksy.model.Recipe;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
class RecipeRepositoryTest {

    @Autowired
    private RecipeRepository repository;

    @Test
    void should_delete_recipe_by_recipe_id() {
        // given
        Long recipeId = 1L;
        Recipe recipe = new Recipe();
        recipe.setRecipeId(recipeId);
        recipe.setTittle("Recipe title for testing");
        repository.save(recipe);

        // when
        List<Recipe> beforeRecipes = (List<Recipe>) repository.findAll();
        repository.deleteRecipeByRecipeId(recipeId);
        List<Recipe> afterRecipes = (List<Recipe>) repository.findAll();

        // then
        assertAll(() -> assertEquals(beforeRecipes.size() - 1, afterRecipes.size()),
                () -> assertTrue(beforeRecipes.contains(recipe)),
                () -> assertFalse(afterRecipes.contains(recipe)));
    }
}
