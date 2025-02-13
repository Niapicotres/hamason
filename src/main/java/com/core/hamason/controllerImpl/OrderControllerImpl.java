package com.core.hamason.controllerImpl;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.core.hamason.controller.IOrderController;
import com.core.hamason.data.model.Order;
import com.core.hamason.service.IOrderService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class OrderControllerImpl implements IOrderController {

    @Autowired
    private IOrderService orderService;

    @Override
    @GetMapping("/orders")
    public String orderListGet(Principal principal, Model model, HttpServletRequest request) {
        log.info("Cargando la lista de pedidos");
        model.addAttribute("orderList", orderService.getAllOrders());
        return "order/orderList";
    }

    @Override
    @GetMapping("/order/viewGet/{id}")
    public String orderViewGet(@PathVariable("id") Long id, Principal principal, Model model, HttpServletRequest request) {
        log.info("Visualizando pedido con ID: " + id);
        model.addAttribute("order", orderService.getOrderById(id).orElse(null));
        return "order/orderView";
    }

    @Override
    @GetMapping("/order/updateGet/{id}")
    public String orderUpdateGet(@PathVariable("id") Long id, Principal principal, Model model, HttpServletRequest request) {
        log.info("Actualizando pedido con ID: " + id);
        model.addAttribute("order", orderService.getOrderById(id).orElse(null));
        return "order/orderUpdate";
    }

    @Override
    @PostMapping("/order/updatePost")
    public String orderUpdatePost(@Valid Order order, BindingResult bindingResult, Principal principal, Model model, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            log.error("Errores en el formulario de pedido: " + bindingResult.getAllErrors());
            return "redirect:/order/updateGet/" + order.getId();
        }
        orderService.saveOrder(order);
        return "redirect:/orders";
    }

    @Override
    @GetMapping("/order/addGet")
    public String orderAddGet(Principal principal, Model model, HttpServletRequest request) {
        log.info("Cargando formulario para agregar un nuevo pedido");
        model.addAttribute("order", new Order());
        return "order/orderAdd";
    }

    @Override
    @PostMapping("/order/addPost")
    public String orderAddPost(@Valid Order order, BindingResult bindingResult, Principal principal, Model model, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            log.error("Errores en el formulario de pedido: " + bindingResult.getAllErrors());
            return "order/orderAdd";
        }
        orderService.saveOrder(order);
        return "redirect:/orders";
    }

    @Override
    @GetMapping("/order/deleteGet/{id}")
    public String orderDeleteGet(@PathVariable("id") Long id, Principal principal, Model model, HttpServletRequest request) {
        log.info("Cargando página de eliminación para el pedido con ID: " + id);
        model.addAttribute("order", orderService.getOrderById(id).orElse(null));
        return "order/orderDelete";
    }

    @Override
    @GetMapping("/order/deleteConfirmed/{id}")
    public String orderDeleteConfirmed(@PathVariable("id") Long id, Principal principal, Model model, HttpServletRequest request) {
        log.info("Eliminando pedido con ID: " + id);
        orderService.deleteOrder(id);
        return "redirect:/orders";
    }
}
