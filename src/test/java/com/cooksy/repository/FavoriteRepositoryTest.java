package com.cooksy.repository;

import java.util.List;
import com.cooksy.model.Favorite;
import com.cooksy.model.Recipe;
import com.cooksy.model.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
class FavoriteRepositoryTest {

    @Autowired
    private FavoriteRepository repository;

    @Test
    void should_find_by_user() {
        // given
        Favorite favoriteSample = getFavoriteSample();
        User userSample = favoriteSample.getUser();

        // when
        List<Favorite> favoritesByUser = repository.findByUser(userSample);
        Favorite foundFavorite = favoritesByUser.stream()
                .filter(f -> f.getFavoriteId().equals(favoriteSample.getFavoriteId()))
                .findFirst().orElse(new Favorite());

        // then
        assertAll(() -> assertEquals(favoriteSample, foundFavorite),
                () -> assertEquals(favoriteSample.getRecipe(), foundFavorite.getRecipe()),
                () -> assertEquals(favoriteSample.getUser(), foundFavorite.getUser()));
    }

    @Test
    void should_find_by_user_and_recipe() {
        // given
        Favorite favoriteSample = getFavoriteSample();
        User userSample = favoriteSample.getUser();
        Recipe recipeSample = favoriteSample.getRecipe();

        // when
        Favorite foundFavorite = repository.findByUserAndRecipe(userSample, recipeSample).orElse(new Favorite());

        // then
        assertAll(() -> assertEquals(favoriteSample, foundFavorite),
                () -> assertEquals(favoriteSample.getRecipe(), foundFavorite.getRecipe()),
                () -> assertEquals(favoriteSample.getUser(), foundFavorite.getUser()));
    }

    @Test
    void should_find_by_recipe() {
        // given
        Favorite favoriteSample = getFavoriteSample();
        Recipe recipeSample = favoriteSample.getRecipe();

        // when
        Favorite foundFavorite = repository.findByRecipe(recipeSample).orElse(new Favorite());

        // then
        assertAll(() -> assertEquals(favoriteSample, foundFavorite),
                () -> assertEquals(favoriteSample.getRecipe(), foundFavorite.getRecipe()),
                () -> assertEquals(favoriteSample.getUser(), foundFavorite.getUser()));
    }

    @Test
    void should_delete_by_favorite_id() {
        // given
        Favorite favorite = getFavoriteSample();
        Long favoriteId = favorite.getFavoriteId();

        // when
        List<Favorite> beforeFavorites = (List<Favorite>) repository.findAll();
        repository.deleteByFavoriteId(favoriteId);
        List<Favorite> afterFavorites = (List<Favorite>) repository.findAll();
        Favorite foundFavorite = beforeFavorites.stream()
                .filter(f -> f.getFavoriteId().equals(favorite.getFavoriteId()))
                .findFirst().orElse(new Favorite());


        // then
        assertAll(() -> assertEquals(beforeFavorites.size() - 1, afterFavorites.size()),
                () -> assertFalse(afterFavorites.contains(favorite)),
                () -> assertEquals(foundFavorite.getFavoriteId(), favorite.getFavoriteId()));
    }

    private Favorite getFavoriteSample() {
        Long recipeId = 2L;
        Long userId = 3L;
        Long favoriteId = 1L;

        Recipe recipe = new Recipe();
        recipe.setRecipeId(recipeId);
        recipe.setTittle("Recipe title for testing");

        User user = new User();
        user.setUserId(userId);
        user.setFirstName("User name for testing");

        return repository.save(new Favorite(favoriteId, user, recipe));
    }
}
