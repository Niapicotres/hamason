package com.core.hamason.serviceImpl;

import com.core.hamason.data.model.FamilyCategory;
import com.core.hamason.data.model.Product;
import com.core.hamason.data.repository.IFamilyCategoryRepository;
import com.core.hamason.data.repository.IProductRepository;
import com.core.hamason.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements IProductService {

    private final IProductRepository productRepository;
    private final IFamilyCategoryRepository familyCategoryRepository;

    @Autowired
    public ProductServiceImpl(IProductRepository productRepository, IFamilyCategoryRepository familyCategoryRepository) {
        this.productRepository = productRepository;
        this.familyCategoryRepository = familyCategoryRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> getProductsByFamilia(String familia) {
        FamilyCategory category = familyCategoryRepository.findByNombre(familia)
            .orElseThrow(() -> new RuntimeException("Categoría no encontrada: " + familia));

        return productRepository.findByFamilyCategory(category);
    }
}
