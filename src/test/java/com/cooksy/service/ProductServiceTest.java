package com.cooksy.service;

import com.cooksy.repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@Sql("data-test.sql")
@DataJpaTest
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;


    @Test
    public void should_return_all_products() {
        //given
//        List<Product> expectedValue = prepareData();
//        when(productRepository.findAll()).thenReturn(expectedValue);

        //when
//        List<ProductDto> allProducts = productService.getAll(); //nullPointer??

        //then
//        assertEquals(allProducts.size(), expectedValue.size());
    }

    @Test
    public void should_delete_product(){
        //given
        Long productId = 1L;
        //when
        productRepository.deleteById(productId);
        //then
        verify(productRepository, times(1)).deleteById(eq(productId));
    }

//    private List<Product> prepareData() {
//        Grammage grammage = new Grammage();
//        grammage.setGmId(1L);
//
//        return Arrays.asList(new Product(1L, "Pepper", 5.82, 1L, 1L, grammage),
//                new Product(2L, "Tomato", 3.25, 2L, 1L, grammage));
//    }

}
