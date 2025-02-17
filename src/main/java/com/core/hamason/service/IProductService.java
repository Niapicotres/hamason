package com.core.hamason.service;

import com.core.hamason.data.model.Product;
import java.util.List;
import java.util.Optional;

public interface IProductService {
    List<Product> getAllProducts();
    List<Product> getProductsByFamily(String familia);
    
    Optional<Product> getProductById(Long id);
    Product saveProduct(Product product);
    void deleteProduct(Long id);
    
}
 