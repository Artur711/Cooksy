package com.cooksy.repository;

import com.cooksy.model.Product;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@Sql("data-test.sql")
@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void should_delete_by_product_id() {
        //given
        Long productID = 2069L;

        // when
        List<Product> beforeProducts = (List<Product>) productRepository.findAll();
        productRepository.deleteByProductId(productID);
        List<Product> afterProducts = (List<Product>) productRepository.findAll();

        // then
        assertAll(() ->assertEquals(3, afterProducts.size()),
                () ->assertEquals(4, beforeProducts.size()));
    }
}
