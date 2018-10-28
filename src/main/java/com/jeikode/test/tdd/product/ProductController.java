package com.jeikode.test.tdd.product;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products/{id}")
    private Product getProduct(@PathVariable Long id){
        return productService.getProductById(id);
    }

    @PostMapping("/products")
    @ResponseStatus(HttpStatus.CREATED)
    private Product createProduct(@RequestBody Product product){
        return productService.createProduct(product);
    }

    @PutMapping("/products/{id}")
    private Product updateProduct(@PathVariable Long id,
                                  @RequestBody Product product){
        return productService.updateProduct(id, product);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private void handleProductNotFound(ProductNotFoundException ex){}
}
