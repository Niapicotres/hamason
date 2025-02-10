package com.core.hamason.serviceImpl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.hamason.data.model.FamilyCategory;
import com.core.hamason.data.repository.IFamilyCategoryRepository;
import com.core.hamason.service.IFamilyCategoryService;

@Service
public class FamilyCategoryServiceImpl implements IFamilyCategoryService {
	
	@Autowired
	private IFamilyCategoryRepository familyCategoryRepository;

	@Override
	public FamilyCategory save(FamilyCategory familyCategory) {
		return this.familyCategoryRepository.save(familyCategory);
	}

	@Override
	public Optional<FamilyCategory> findById(Long id) {
		return this.familyCategoryRepository.findById(id);
	}

	@Override
	public List<FamilyCategory> findAll() {
		return this.familyCategoryRepository.findAll(); 
	}

	@Override
	public void deleteById(Long id) {
		familyCategoryRepository.deleteById(id);
		
	}

}
