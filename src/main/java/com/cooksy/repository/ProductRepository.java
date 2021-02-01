package com.cooksy.repository;

import com.cooksy.model.Product;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ProductRepository extends CrudRepository<Product,Long> {

    @Transactional
    @Modifying
    @Query(value = "delete from products where product_id = :productId", nativeQuery = true)
    void deleteByProductId(@Param("productId") Long productId);
}
