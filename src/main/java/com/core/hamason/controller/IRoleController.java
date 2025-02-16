package com.core.hamason.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.core.hamason.data.model.Role;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
public interface IRoleController {
    
    public String roleListGet(Principal principal, Model model, HttpServletRequest request);
    
    public String roleViewGet(String roleName, Principal principal, Model model, HttpServletRequest request);
    
    public String roleAddGet(Principal principal, Model model, HttpServletRequest request);
    
    public String roleAddPost(@Valid Role role, BindingResult bindingResult, Principal principal, Model model, HttpServletRequest request);
    
    public String roleDeleteGet(String roleName, Principal principal, Model model, HttpServletRequest request);
    
    public String roleDeleteConfirmed(String roleName, Principal principal, Model model, HttpServletRequest request);
    
    public String roleUpdateGet(String roleName, Principal principal, Model model, HttpServletRequest request);
    
    public String roleUpdatePost(@Valid Role role, BindingResult bindingResult, Principal principal, Model model, HttpServletRequest request);
}
