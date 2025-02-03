package com.core.hamason.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.core.hamason.data.model.Encryption;

@Service
public interface IEncryptionService {

	public Encryption newEntity();
	public Encryption encryptText(Encryption encryption);
	public List<String> findAllEncodersName();
	public List<PasswordEncoder> getEncoderList();

}
