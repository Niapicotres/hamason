package com.core.hamason.data.model;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Encryption {
	
	@Size(min=1, max=50, message="textToEncrypt must have from 1 to 50 characters")
	private String textToEncrypt;
	
	private String passwordEncoderString;
	
	@Size(min=1, max=255, message="textEncrypted must have from 1 to 255 characters")
	private String textEncrypted;

}
