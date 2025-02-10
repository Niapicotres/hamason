package com.core.hamason.data.repository;

import com.core.hamason.data.model.FamilyCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface IFamilyCategoryRepository extends JpaRepository<FamilyCategory, Long> {
    Optional<FamilyCategory> findByNombre(String nombre);
}
