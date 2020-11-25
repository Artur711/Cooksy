package com.cooksy.backdoor;

import com.cooksy.dto.ProductDto;
import com.cooksy.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v2/product")
public class ProductBackDoor {

    private final ProductService productService;
    @GetMapping
    public List<ProductDto> getAllProducts(){
        return productService.getAll();
    }
    @PostMapping
    public void saveProduct(@RequestBody ProductDto productDto){
        productService.saveProduct(productDto);
    }
}
