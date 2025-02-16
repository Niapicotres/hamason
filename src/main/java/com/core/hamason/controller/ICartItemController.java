package com.core.hamason.controller;

import org.springframework.ui.Model;

public interface ICartItemController {
    String addItemToCart(Long cartId, Long productId, int quantity);
    String removeItemFromCart(Long cartItemId);
    String showCartItems(Long cartId, Model model);
}