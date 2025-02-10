package com.core.hamason.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.core.hamason.data.model.FamilyCategory;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;


@Controller
public interface IFamilyCategoryController {
    
    public String familyCategoryListGet(Principal principal, Model model, HttpServletRequest request);
    
    public String familyCategoryViewGet(Long id, Principal principal, Model model, HttpServletRequest request);
    
    public String familyCategoryAddGet(Principal principal, Model model, HttpServletRequest request);
    
    public String familyCategoryAddPost(@Valid FamilyCategory familyCategory, BindingResult bindingResult, Principal principal, Model model, HttpServletRequest request);
    
    public String familyCategoryDeleteGet( Long id,
            Principal principal, 
            Model model,
            HttpServletRequest request);
    
    public String familyCategoryDeleteConfirmed( Long id,
            Principal principal, 
            Model model,
            HttpServletRequest request);
    
    public String familyCategoryUpdateGet( Long id,
            Principal principal, 
            Model model,
            HttpServletRequest request);
    
    public String familyCategoryUpdatePost(FamilyCategory familyCategory, BindingResult bindingResult, Principal principal, Model model, HttpServletRequest request);
}
 