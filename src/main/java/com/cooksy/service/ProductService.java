package com.cooksy.service;

import com.cooksy.dto.Id;
import com.cooksy.dto.ProductDto;
import com.cooksy.exception.UserNotFoundException;
import com.cooksy.model.Product;
import com.cooksy.repository.ProductRepository;
import com.cooksy.util.converter.ProductDtoToProductConverter;
import com.cooksy.util.converter.ProductToProductDtoConverter;
import lombok.AllArgsConstructor;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductToProductDtoConverter productToProductDtoConverter;
    private final ProductDtoToProductConverter productDtoToProductConverter;


    public List<ProductDto> getAll() {
        return productToProductDtoConverter.convertAll((List<Product>) productRepository.findAll());
    }

    public void saveProduct(ProductDto productDto) {
        productRepository.save(productDtoToProductConverter.convert(productDto));
    }

    public ProductDto getProductByID(Id id) {

        return productToProductDtoConverter.convert(productRepository.findById(id.getValue())
                .orElseThrow(() -> new UserNotFoundException(id)));
    }

    public void deleteProduct(String productID) {
        productRepository.deleteById(Long.parseLong(productID));
    }

    public void updateTheProduct(String id, ProductDto productDto) {
        Product convertedProduct = productDtoToProductConverter.convert(productDto);
        convertedProduct.setProductID(Long.parseLong(id));
        productRepository.save(convertedProduct);
    }
}
