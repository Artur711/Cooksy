package com.cooksy.service;

import com.cooksy.dto.ProductDto;
import com.cooksy.model.Product;
import com.cooksy.repository.ProductRepository;
import com.cooksy.util.converter.ProductDtoToProductConverter;
import com.cooksy.util.converter.ProductToProductDtoConverter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductToProductDtoConverter productToProductDtoConverter;

    @Mock
    private ProductDtoToProductConverter productDtoToProductConverter;

    @InjectMocks
    private ProductService productService;

    @Test
    public void should_return_all_products() {
        //given
        List<Product> expectedValue = prepareData();
        when(productRepository.findAll()).thenReturn(expectedValue);

        //when
        List<ProductDto> allProducts = productService.getAll();

        //then
        assertEquals(4, expectedValue.size());
    }

    @Test
    public void should_delete_product(){
        //given
        Long productId = 2069L;
        //when
        productRepository.deleteById(productId);
        //then
        verify(productRepository, times(1)).deleteById(eq(productId));
    }

    private List<Product> prepareData() {
        return Arrays.asList(new Product(2069L, "balsamic vinegar","3 tablespoons balsamic vinegar", 3, "tablespoons", 3, "Tbsps"),
                new Product(11215L, "garlic", "1 clove garlic, minced", 1, "clove", 1, "clove"),
        new Product(11233L, "kale", "1 bunch curly kale, stems removed and chopped", 1, "bunch", 1, "bunch"),
        new Product(4053L, "olive oil", "Olive oil", 2, "servings", 2, "servings"));
    }
}
