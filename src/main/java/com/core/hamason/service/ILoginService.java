package com.core.hamason.service;

import com.core.hamason.data.model.Login;
import org.springframework.stereotype.Service;

@Service
public interface ILoginService {

    boolean validateCredentials(Login login);
    
    
}
