package com.core.hamason.controllerImpl;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.core.hamason.controller.IProductController;
import com.core.hamason.data.model.Product;
import com.core.hamason.service.IProductService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ProductControllerImpl implements IProductController {

    @Autowired
    private IProductService productService;

    @Override
    @GetMapping("/productListGet")
    public String productListGet(Principal principal, Model model, HttpServletRequest request) {
        log.info("Cargando la lista de productos");
        model.addAttribute("productList", productService.getAllProducts());
        return "product/productList";
    }

    @Override
    @GetMapping("/product/viewGet/{id}")
    public String productViewGet(@PathVariable("id") Long id, Principal principal, Model model, HttpServletRequest request) {
        log.info("Visualizando producto con ID: " + id);
        model.addAttribute("product", productService.getProductById(id).orElse(null));
        return "product/productView";
    }

    @Override
    @GetMapping("/product/updateGet/{id}")
    public String productUpdateGet(@PathVariable("id") Long id, Principal principal, Model model, HttpServletRequest request) {
        log.info("Actualizando producto con ID: " + id);
        model.addAttribute("product", productService.getProductById(id).orElse(null));
        return "product/productUpdate";
    }

    @Override
    @PostMapping("/product/updatePost")
    public String productUpdatePost(@Valid Product product, BindingResult bindingResult, Principal principal, Model model, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            log.error("Errores en el formulario de producto: " + bindingResult.getAllErrors());
            return "redirect:/product/updateGet/" + product.getId();
        }
        productService.saveProduct(product);
        return "redirect:/productListGet";
    }

    @Override
    @GetMapping("/product/addGet")
    public String productAddGet(Principal principal, Model model, HttpServletRequest request) {
        log.info("Cargando formulario para agregar producto");
        model.addAttribute("product", new Product());
        return "product/productAdd";
    }

    @Override
    @PostMapping("/product/addPost")
    public String productAddPost(@Valid Product product, BindingResult bindingResult, Principal principal, Model model, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            log.error("Errores en el formulario de producto: " + bindingResult.getAllErrors());
            return "product/productAdd";
        }
        productService.saveProduct(product);
        return "redirect:/productListGet";
    }

    @Override
    @GetMapping("/product/deleteGet/{id}")
    public String productDeleteGet(@PathVariable("id") Long id, Principal principal, Model model, HttpServletRequest request) {
        log.info("Cargando página de eliminación para el producto con ID: " + id);
        model.addAttribute("product", productService.getProductById(id).orElse(null));
        return "product/productDelete";
    }

    @Override
    @GetMapping("/product/deleteConfirmed/{id}")
    public String productDeleteConfirmed(@PathVariable("id") Long id, Principal principal, Model model, HttpServletRequest request) {
        log.info("Eliminando producto con ID: " + id);
        productService.deleteProduct(id);
        return "redirect:/productListGet";
    }
}
