package com.cooksy.repository;

import com.cooksy.model.Favorite;
import com.cooksy.model.Recipe;
import com.cooksy.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FavoriteRepository extends CrudRepository<Favorite, Long> {

    @Query("SELECT f FROM Favorite f where f.user = :user")
    List<Favorite> findByUser(@Param("user") User user);

    Optional<Favorite> findByUserAndAndRecipe(User user, Recipe recipe);

//    @Modifying
//    @Query("DELETE FROM Favorite f where f.favoriteId = :id")
//    List<Integer> deleteByFavoriteId(@Param("id") Long id);
}

