package com.cooksy.repository;

import com.cooksy.model.Favorite;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface FavoriteRepository extends CrudRepository<Favorite, Long> {
}

