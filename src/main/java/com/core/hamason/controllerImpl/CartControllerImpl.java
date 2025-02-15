package com.core.hamason.controllerImpl;

import com.core.hamason.controller.ICartController;
import com.core.hamason.data.model.Cart;
import com.core.hamason.data.model.Customer;
import com.core.hamason.service.ICartService;
import com.core.hamason.service.ICustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartControllerImpl implements ICartController {

    private final ICartService cartService;
    private final ICustomerService customerService;

    @Override
    @GetMapping("/{customerId}")
    public String showCart(@PathVariable Long customerId, Model model) {
        Optional<Customer> customer = customerService.findById(customerId);
        if (customer.isPresent()) {
            Optional<Cart> cart = cartService.findByCustomer(customer.get());
            cart.ifPresent(value -> model.addAttribute("cart", value));
        }
        return "cart-view"; // Nombre del template Thymeleaf
    }

    @Override
    @GetMapping("/clear/{customerId}")
    public String clearCart(@PathVariable Long customerId) {
        Optional<Customer> customer = customerService.findById(customerId);
        customer.ifPresent(c -> cartService.findByCustomer(c).ifPresent(cartService::clearCart));
        return "redirect:/cart/" + customerId;
    }
}
