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
        // given
        Long productID = 2069L;
        Product product = productRepository.findById(productID).orElse(new Product());

        // when
        List<Product> beforeProducts = (List<Product>) productRepository.findAll();
        productRepository.deleteByProductId(productID);
        List<Product> afterProducts = (List<Product>) productRepository.findAll();

        // then
        assertAll(() -> assertEquals(beforeProducts.size() - 1,afterProducts.size()),
                () -> assertTrue(beforeProducts.contains(product)),
                () -> assertFalse(afterProducts.contains(product)));
    }

    @Test
    public void should_delete_saved_product() {
        // given
        Long productId = 1L;
        Product product = new Product();
        product.setProductID(productId);
        product.setName("Product name test");
        productRepository.save(product);

        // when
        List<Product> beforeProducts = (List<Product>) productRepository.findAll();
        productRepository.deleteByProductId(productId);
        List<Product> afterProducts = (List<Product>) productRepository.findAll();

        // then
        assertAll(() -> assertEquals(beforeProducts.size() - 1, afterProducts.size()),
                () -> assertTrue(beforeProducts.contains(product)),
                () -> assertFalse(afterProducts.contains(product)));

    }
}
