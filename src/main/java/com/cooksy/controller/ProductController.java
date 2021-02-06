package com.cooksy.controller;

import com.cooksy.dto.Id;
import com.cooksy.dto.ProductDto;
import com.cooksy.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<ProductDto> getAllProducts() {
        return productService.getAll();
    }

    @PostMapping
    public void saveProduct(@RequestBody ProductDto productDto) {
        productService.saveProduct(productDto);
    }

    @PutMapping("/{id}")
    public void updateProduct(@PathVariable String id, @RequestBody ProductDto productDto) {
        productService.updateTheProduct(id, productDto);
    }

    @DeleteMapping("/{id}")
    public void deleteTheUser(@PathVariable String id) {
        productService.deleteProduct(Id.idFromString(id));
    }

    @GetMapping("/{id}")
    public ProductDto getTheProduct(@PathVariable String id) {
        return productService.getProductByID(Id.idFromString(id));
    }
}
