package com.core.hamason.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.core.hamason.data.model.FamilyCategory;


@Repository
public interface IFamilyCategoryRepository extends JpaRepository<FamilyCategory, Long> {

}
