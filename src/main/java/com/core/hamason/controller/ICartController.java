package com.core.hamason.controller;

import org.springframework.ui.Model;

public interface ICartController {
    String showCart(Long customerId, Model model);
    String clearCart(Long customerId);
}
