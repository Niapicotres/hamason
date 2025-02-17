package com.core.hamason.controller;

import java.security.Principal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestParam;

import com.core.hamason.data.model.Product;
//import com.core.hamason.data.model.Familia;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
public interface IProductController {

    public String productListGet(Principal principal, Model model, HttpServletRequest request);
    
    public String listProductsByCategory(@RequestParam("categoria") String categoria, Principal principal, Model model, HttpServletRequest request);
    
    
    public String productViewGet(Long id, Principal principal, Model model, HttpServletRequest request);
    
    public String productUpdateGet(Long id, Principal principal, Model model, HttpServletRequest request);
    
    public String productUpdatePost(@Valid Product product, BindingResult bindingResult, Principal principal, Model model, HttpServletRequest request);
    
    public String productDeleteGet(Long id, Principal principal, Model model, HttpServletRequest request);
    
    public String productDeleteConfirmed(Long id, Principal principal, Model model, HttpServletRequest request);
    
    public String productAddGet(Principal principal, Model model, HttpServletRequest request);
    
    public String productAddPost(@Valid Product product, BindingResult bindingResult, Principal principal, Model model, HttpServletRequest request);
    
 

}
