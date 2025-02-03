package com.core.hamason.service;

import java.util.Optional;
import java.util.List;

import org.springframework.stereotype.Service;

import com.core.hamason.data.model.Status;

@Service
public interface IStatusService {
	
	public Status save(Status status);
	
	public Optional<Status> findById(Long id);
	
	public List<Status> findAll();

}
