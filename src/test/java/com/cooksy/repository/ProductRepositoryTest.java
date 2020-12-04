package com.cooksy.repository;

import com.cooksy.model.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@Sql("data-test.sql")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void should_find_by_productId() {
        //given
        Long productID = 1L;
        //when
        List<Product> products = (List<Product>) productRepository.findAllById(Collections.singleton(productID));

        //then
        assertAll(
                () -> assertEquals(5.82, products.get(0).getPrice()),
                () -> assertEquals("Pepper", products.get(0).getName()),
                () -> assertEquals(1, products.size())
        );
    }

}