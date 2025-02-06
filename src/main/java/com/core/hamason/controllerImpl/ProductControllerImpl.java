package com.core.hamason.controllerImpl;

import com.core.hamason.controller.IProductController;
import com.core.hamason.data.model.Product;
import com.core.hamason.service.IProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/hamason")
public class ProductControllerImpl implements IProductController {

    private final IProductService productService;

    public ProductControllerImpl(IProductService productService) {
        this.productService = productService;
    }

    @Override
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @Override
    public ResponseEntity<Optional<Product>> getProductById(@PathVariable Long id) {
        Optional<Product> product = productService.getProductById(id);
        return product.isPresent() ? ResponseEntity.ok(product) : ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product savedProduct = productService.saveProduct(product);
        return ResponseEntity.ok(savedProduct);
    }

    @Override
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        if (productService.getProductById(id).isPresent()) {
            productService.deleteProduct(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<List<Product>> getProductsByFamilia(@PathVariable String familia) {
        List<Product> products = productService.getProductsByFamilia(familia);
        return ResponseEntity.ok(products);
    }
}
