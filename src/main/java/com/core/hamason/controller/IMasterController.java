package com.core.hamason.controller;

import java.security.Principal;

import org.springframework.ui.Model;

import jakarta.servlet.http.HttpServletRequest;

public interface IMasterController {
	
	public void injectCommonAttributesInHtmlPage(
			Principal principal,
			Model model,
			HttpServletRequest request);

}
