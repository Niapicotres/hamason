package com.core.hamason.serviceImpl;

import com.core.hamason.data.model.Login;
import com.core.hamason.data.model.User;
import com.core.hamason.service.ILoginService;
import com.core.hamason.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements ILoginService {

    @Autowired
    private IUserService userService; // Para buscar el usuario en BD
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean validateCredentials(Login login) {
        // Buscar el usuario por username
        return userService.findByUsername(login.getUsername())
            .map((User user) -> {
                // Comparar contraseñas (cifrado)
                return passwordEncoder.matches(login.getPassword(), user.getPassword());
            })
            .orElse(false);
    }
}
