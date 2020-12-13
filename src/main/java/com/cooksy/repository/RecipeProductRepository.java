package com.cooksy.repository;

import com.cooksy.model.RecipeProduct;
import org.springframework.data.repository.CrudRepository;

public interface RecipeProductRepository extends CrudRepository<RecipeProduct, Long> {
}
