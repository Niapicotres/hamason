package com.core.hamason.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.core.hamason.data.model.Encryption;
import com.core.hamason.data.model.Login;
import com.core.hamason.service.IEncryptionService;
import com.core.hamason.service.ILoginService;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Service
@Getter
@Slf4j
public class EncryptionServiceImpl implements IEncryptionService {
	
	@Autowired
	private List<PasswordEncoder> encoderList = new ArrayList<PasswordEncoder>();
	
	@Override
	public Encryption newEntity() {
		return new Encryption();
	}

	@Override
	public Encryption encryptText(Encryption encryption) {
		//get textToEncrypt & algorithm's name 
		//obtain the passwordEncoder related to algoritmh's name
		 Optional<PasswordEncoder>  passwordEncoder = this.encoderList
			.stream()
			.filter(x ->  x.getClass().getSimpleName().replace("PasswordEncoder", "").equals(encryption.getPasswordEncoderString()))
			.findFirst(); //nos devuelve el optional
		
		 if(passwordEncoder.isEmpty()) {
			 encryption.setTextEncrypted("ERROR - NO ECRYPTION ALGORITHM");
		 }
		 else {
			 encryption.setTextEncrypted(passwordEncoder.get().encode(encryption.getTextToEncrypt()));
		 } 
		 
		//calcular & return the Encryption
		return encryption ;
	}

	@Override
	public List<String> findAllEncodersName() {
		log.info("Beans - names of PasswordEncoder class registered in Spring Container: ");
		return this.encoderList
				.stream()
				//.peek(x -> log.info("\t> " + x.toString()))
				.peek(x -> log.info("\t\t>> " + x.getClass().getSimpleName().replace("PasswordEncoder", "")))
				.map(x -> x.getClass().getSimpleName().replace("PasswordEncoder", ""))
				.collect(Collectors.toList());
	}

}
