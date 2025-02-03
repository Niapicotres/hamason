package com.core.hamason.serviceImpl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.hamason.data.model.Status;
import com.core.hamason.data.repository.IStatusRepository;
import com.core.hamason.service.IStatusService;

@Service
public class StatusServiceImpl implements IStatusService {
	
	@Autowired
	private IStatusRepository statusRepository;

	@Override
	public Status save(Status status) {
		return this.statusRepository.save(status);
	}

	@Override
	public Optional<Status> findById(Long id) {
		return this.statusRepository.findById(id);
	}

	@Override
	public List<Status> findAll() {
		return this.statusRepository.findAll();
	}

}
