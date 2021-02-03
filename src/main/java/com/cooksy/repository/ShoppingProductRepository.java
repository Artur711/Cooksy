package com.cooksy.repository;

import com.cooksy.model.ShoppingProduct;
import com.cooksy.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface ShoppingProductRepository extends CrudRepository<ShoppingProduct, Long> {

    List<ShoppingProduct> findAllByUser(User user);
}
