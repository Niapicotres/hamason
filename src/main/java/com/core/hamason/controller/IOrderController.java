package com.core.hamason.controller;

import com.core.hamason.data.model.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import java.security.Principal;

@Controller
public interface IOrderController {

    // Mostrar lista de pedidos
    public String orderListGet(Principal principal, Model model, HttpServletRequest request);
    
    // Ver detalles de un pedido por ID
    public String orderViewGet(Long id, Principal principal, Model model, HttpServletRequest request);
    
    // Obtener formulario para actualizar un pedido
    public String orderUpdateGet(Long id, Principal principal, Model model, HttpServletRequest request);
    
    // Actualizar un pedido (POST)
    public String orderUpdatePost(@Valid Order order, BindingResult bindingResult, Principal principal, Model model, HttpServletRequest request);
    
    // Obtener formulario para eliminar un pedido
    public String orderDeleteGet(Long id, Principal principal, Model model, HttpServletRequest request);
    
    // Confirmación de eliminación de un pedido
    public String orderDeleteConfirmed(Long id, Principal principal, Model model, HttpServletRequest request);
    
    // Obtener formulario para añadir un nuevo pedido
    public String orderAddGet(Principal principal, Model model, HttpServletRequest request);
    
    // Añadir un nuevo pedido (POST)
    public String orderAddPost(@Valid Order order, BindingResult bindingResult, Principal principal, Model model, HttpServletRequest request);
}

