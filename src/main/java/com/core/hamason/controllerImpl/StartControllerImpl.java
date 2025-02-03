package com.core.hamason.controllerImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.core.hamason.config.LanguageResourceBundleMessage;
import com.core.hamason.controller.IStartController;
import com.core.hamason.data.model.Encryption;
import com.core.hamason.data.model.Login;
import com.core.hamason.service.IEncryptionService;
import com.core.hamason.service.ILoginService;
import com.core.hamason.service.ISqlScriptCreatorService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

import java.security.NoSuchAlgorithmException;
import java.security.Principal;

@Controller
@Slf4j
public class StartControllerImpl 
	extends MasterControllerImpl
	implements IStartController {

	@Autowired
	private LanguageResourceBundleMessage languageResourceBundleMessage; 
	
	@Autowired
	private ILoginService loginService;

	@Autowired
	private IEncryptionService encryptionService;

	@Autowired
	private ISqlScriptCreatorService sqlCreatorServiceImpl;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	@GetMapping({"/dumpDBGet"})
	public String dumpDBGet() {
		System.out.println("TRAZA dumpDBGet");
		// 
		sqlCreatorServiceImpl.dumpDB();
		//
		return "masterFull";
	}

	@Override
	@GetMapping({"/logoutGet"})
    public String logoutGet(
    		Principal principal, 
    		Model model, 
    		HttpServletRequest request) {
		System.out.println("TRAZA logoutGet");
		// Invalidate session
		request.getSession().invalidate();
		//
		// Inject data into html page
		model.addAttribute("login", loginService.newEntity());
		//
		//return "redirect:/loginGet?logoutOk";
		return "loginPage";
	}
	
	@Override
	@GetMapping({"/loginGet"})
	public String loginGet(Model model) {
		System.out.println("TRAZA loginGet");
		// Inject data into html page
		model.addAttribute("login", loginService.newEntity());
		//
		return "loginPage";
	}

	@Override
	@PostMapping({"/loginPost"})
	public String loginPost(
			@Valid Login login,
			BindingResult bindingResult,
			HttpServletRequest request,
			Model model) {
		System.out.println("TRAZA loginPost");
		if (bindingResult.hasErrors()) {
			log.warn("VALIDATION ERRORS!!!: " + bindingResult.getAllErrors());
			return "loginPage";
		}
		else {
			UsernamePasswordAuthenticationToken authenticationToken =
					new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword());
			Authentication authentication = authenticationManager.authenticate(authenticationToken);
			if (authentication.isAuthenticated()) {
				SecurityContext securityContext = SecurityContextHolder.getContext();
				securityContext.setAuthentication(authentication);
				//
				HttpSession session = request.getSession(true);
				session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, securityContext);
			} 
			else {
				log.info("BAD CREDENTIALS! NOT LOGGED");
				return "loginPage";
			}			
		}
		return "redirect:/homeGet";
	}

	@Override
	@GetMapping({"/homeGet"})
	public String homeGet(Principal principal, Model model, HttpServletRequest request) {
		System.out.println("TRAZA homeGet");
		// Inject data into html page
		this.injectCommonAttributesInHtmlPage(principal, model, request);
		//
		/*
		// Testing NifValidator method:
		NifValidator.nifIsCorrectAndNotNull("12345678A");
		NifValidator.nifIsCorrectAndNotNull("12345678Z");
		NifValidator.nifIsCorrectAndNotNull("12345678z");
		NifValidator.nifIsCorrectAndNotNull("abc45678");
		NifValidator.nifIsCorrectAndNotNull(null);
		*/
		/*
		// Check Max key size for encryption:
		try {
			int maxKeySize = javax.crypto.Cipher.getMaxAllowedKeyLength("AES");  //Other algorithms: RC5,...
			log.info("Max key size for AES algorithm= " + maxKeySize);
		} catch (NoSuchAlgorithmException e) {
			//e.printStackTrace();
			log.error("EXCEPTION when calculating Max key size for AES algorithm= " + e.getMessage());
		}
		*/
		//
		encryptionService.findAllEncodersName();
		Encryption encryption = encryptionService.newEntity();
		encryption.setTextToEncrypt("En un lugar de la Mancha..");
		encryption.setPasswordEncoderString("BCrypt");
		log.info("El texto encriptado es: " + encryptionService.encryptText(encryption).getTextEncrypted());
		//
		return "masterFull";
	}

	@Override
	public void injectCommonAttributesInHtmlPage(
			Principal principal, 
			Model model, 
			HttpServletRequest request) {

		model.addAttribute("username", request.getUserPrincipal().getName());
		model.addAttribute("userPicture", "");
		
		model.addAttribute("languageTagStringList", languageResourceBundleMessage.getLanguageTagStringListFromResourceArray());
		model.addAttribute("requestURI", request.getRequestURI());
		
		log.warn("languageTagStringList: " + languageResourceBundleMessage.getLanguageTagStringListFromResourceArray());
		log.warn("requestURI: ", request.getRequestURI());
		
	}
	
}
