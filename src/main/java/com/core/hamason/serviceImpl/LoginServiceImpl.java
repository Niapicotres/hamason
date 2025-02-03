package com.core.hamason.serviceImpl;

import org.springframework.stereotype.Service;

import com.core.hamason.data.model.Login;
import com.core.hamason.service.ILoginService;

@Service
public class LoginServiceImpl implements ILoginService {
	
	@Override
	public Login newEntity() {
		return new Login();
	}

}
