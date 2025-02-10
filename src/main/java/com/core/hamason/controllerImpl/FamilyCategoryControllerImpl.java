package com.core.hamason.controllerImpl;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.core.hamason.controller.IFamilyCategoryController;

import com.core.hamason.data.model.FamilyCategory;
import com.core.hamason.service.IFamilyCategoryService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;   

@Slf4j
@Controller
public class FamilyCategoryControllerImpl implements IFamilyCategoryController {
     
    @Autowired
    private IFamilyCategoryService familyService;

    @Override
    @PostMapping({"/familyCategory/addPost"})
    public String familyCategoryAddPost(
            @Valid FamilyCategory familyCategory,
            BindingResult bindingResult,
            Principal principal, 
            Model model,
            HttpServletRequest request) {
        System.out.println("TRAZA familyAddPost");
        if (bindingResult.hasErrors()) {
            log.error("El formulario de Family tiene errores: " + bindingResult.getAllErrors());
            return "family/familyAdd";
        } else {
            log.info("Formulario correcto: " + familyCategory);
            this.familyService.save(familyCategory);
            return "redirect:/familyListGet";
        }
    }

    @Override
    @GetMapping({"/familyCategory/addGet"})
    public String familyCategoryAddGet(
            Principal principal, 
            Model model,
            HttpServletRequest request) {
        System.out.println("TRAZA familyAddGet");
        model.addAttribute("family", new FamilyCategory());
        return "family/familyAdd";
    }

    @Override
    @GetMapping({"/familyCategory/viewGet/{id}"})
    public String familyCategoryViewGet(
            @PathVariable("id") Long id,
            Principal principal, 
            Model model,
            HttpServletRequest request) {
        System.out.println("TRAZA familyViewGet");
        model.addAttribute("family", this.familyService.findById(id).get());
        return "familyCategory/familyView";
    }

    @Override
    @GetMapping({"/familyCategory/updateGet/{id}"})
    public String familyCategoryUpdateGet(
            @PathVariable("id") Long id,
            Principal principal, 
            Model model,
            HttpServletRequest request) {
        System.out.println("TRAZA familyUpdateGet");
        model.addAttribute("family", this.familyService.findById(id).get());
        return "familyCategory/familyUpdate";
    }

    @Override
    @PostMapping({"/familyCategory/updatePost"})
    public String familyCategoryUpdatePost(
            @Valid FamilyCategory familyCategory,
            BindingResult bindingResult,
            Principal principal, 
            Model model,
            HttpServletRequest request) {
        System.out.println("TRAZA familyUpdatePost");
        if (bindingResult.hasErrors()) {
            log.error("El formulario de Family tiene errores: " + bindingResult.getAllErrors());
            return "redirect:/familyCategory/updateGet/" + familyCategory.getId();
        } else {
            log.info("Formulario correcto: " + familyCategory);
            this.familyService.save(familyCategory);
            return "redirect:/familyCategoryListGet";
        }
    }

    @Override
    @GetMapping({"/familyCategory/deleteGet/{id}"})
    public String familyCategoryDeleteGet(
            @PathVariable("id") Long id,
            Principal principal, 
            Model model,
            HttpServletRequest request) {
        System.out.println("TRAZA familyDeleteGet");
        model.addAttribute("family", this.familyService.findById(id).get());
        return "familyCategory/familyDelete";
    }

    @Override
    @GetMapping({"/familyCategory/deleteConfirmed/{id}"})
    public String familyCategoryDeleteConfirmed(
            @PathVariable("id") Long id,
            Principal principal, 
            Model model,
            HttpServletRequest request) {
        System.out.println("TRAZA familyDeleteConfirmed");
        this.familyService.deleteById(id);
        return "redirect:/familyCategoryListGet";
    }

    @Override
    @GetMapping({"/familyCategoryListGet"})
    public String familyCategoryListGet(
            Principal principal, 
            Model model, 
            HttpServletRequest request) {
        System.out.println("TRAZA familyListGet");
        model.addAttribute("familyList", this.familyService.findAll());
        return "familyCategory/familyList";
    }
}