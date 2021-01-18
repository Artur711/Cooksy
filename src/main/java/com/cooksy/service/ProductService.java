package com.cooksy.service;

import com.cooksy.dto.Id;
import com.cooksy.dto.ProductDto;
import com.cooksy.exception.UserNotFoundException;
import com.cooksy.model.Product;
import com.cooksy.repository.ProductRepository;
import com.cooksy.util.converter.ProductDtoToProductConverter;
import com.cooksy.util.converter.ProductToProductDtoConverter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductToProductDtoConverter productToProductDtoConverter;
    private final ProductDtoToProductConverter productDtoToProductConverter;

    public List<ProductDto> getAll() {
        List<ProductDto> productsDto = productToProductDtoConverter.convertAll((List<Product>) productRepository.findAll());
        log.info(String.format("Returned %d products", productsDto.size()));
        return productsDto;
    }

    public void saveProduct(ProductDto productDto) {
        productRepository.save(productDtoToProductConverter.convert(productDto));
        log.info(String.format("Saved product [id: %d]", productDto.getProductId()));
    }

    public ProductDto getProductByID(Id id) {
        ProductDto productDto = productToProductDtoConverter.convert(productRepository.findById(id.getValue())
                .orElseThrow(() -> new UserNotFoundException(id)));

        log.info(String.format("Returned product by [id: %d]", id.getValue()));
        return productDto;
    }

    public void deleteProduct(Id id) {
        productRepository.deleteByProductId(id.getValue());
        log.info(String.format("Deleted product by [id: %d]", id.getValue()));
    }

    public void updateTheProduct(String id, ProductDto productDto) {
        Product convertedProduct = productDtoToProductConverter.convert(productDto);
        convertedProduct.setProductID(Long.parseLong(id));
        productRepository.save(convertedProduct);
        log.info(String.format("Updated product by [id: %s]", id));
    }
}
