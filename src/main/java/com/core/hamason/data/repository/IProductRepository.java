package com.core.hamason.data.repository;

import com.core.hamason.data.model.FamilyCategory;
import com.core.hamason.data.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByFamilyCategory(FamilyCategory familyCategory);

}
