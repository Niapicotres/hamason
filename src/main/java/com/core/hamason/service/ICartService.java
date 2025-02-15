package com.core.hamason.service;

import com.core.hamason.data.model.Cart;
import com.core.hamason.data.model.Customer;

import java.util.Optional;

public interface ICartService {
    Optional<Cart> findByCustomer(Customer customer);
    Cart save(Cart cart);
    void clearCart(Cart cart);
}
