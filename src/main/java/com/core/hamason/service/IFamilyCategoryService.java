package com.core.hamason.service;

import java.util.Optional;
import java.util.List;
import org.springframework.stereotype.Service;
import com.core.hamason.data.model.FamilyCategory;


@Service
public interface IFamilyCategoryService {
	
	public FamilyCategory save(FamilyCategory familyCategory);
	
	public Optional<FamilyCategory> findById(Long id);
	
	public List<FamilyCategory> findAll();
	void deleteById(Long id);

}
