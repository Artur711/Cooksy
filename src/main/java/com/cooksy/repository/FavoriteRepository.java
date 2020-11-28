package com.cooksy.repository;

import com.cooksy.model.Favorite;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface FavoriteRepository extends CrudRepository<Favorite, Long> {
    // need all 3 @nnotation to work
    @Transactional
    @Modifying
    @Query(
            value = "insert into Favorites (recipe_id, user_id) values (:recipe_id,:user_id)",
            nativeQuery = true)
    void addRecipeToUserFavorite(@Param("recipe_id") Long recipe_id, @Param("user_id") Long user_id);
}
