package com.core.hamason.controller;

import com.core.hamason.data.model.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public interface IProductController {
    
    @GetMapping
    ResponseEntity<List<Product>> getAllProducts();
    
    @GetMapping("/{id}")
    ResponseEntity<Optional<Product>> getProductById(@PathVariable Long id);
    
    @PostMapping
    ResponseEntity<Product> createProduct(@RequestBody Product product);
    
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteProduct(@PathVariable Long id);
    
    @GetMapping("/familia/{familia}")
    ResponseEntity<List<Product>> getProductsByFamilia(@PathVariable String familia);
}
