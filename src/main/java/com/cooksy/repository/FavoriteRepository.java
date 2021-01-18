package com.cooksy.repository;

import com.cooksy.model.Favorite;
import com.cooksy.model.Recipe;
import com.cooksy.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface FavoriteRepository extends CrudRepository<Favorite, Long> {

    @Query("SELECT f FROM Favorite f where f.user = :user")
    List<Favorite> findByUser(@Param("user") User user);

    Optional<Favorite> findByUserAndRecipe(User user, Recipe recipe);

    Optional<Favorite> findByRecipe(Recipe recipe);

    @Transactional
    @Modifying
    @Query(value = "delete from favorites where favorite_id = :favoriteId", nativeQuery = true)
    void deleteByFavoriteId(@Param("favoriteId") Long favoriteId);
}

