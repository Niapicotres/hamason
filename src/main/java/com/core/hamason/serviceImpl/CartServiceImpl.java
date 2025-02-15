package com.core.hamason.serviceImpl;

import com.core.hamason.data.model.Cart;
import com.core.hamason.data.model.Customer;
import com.core.hamason.data.repository.ICartRepository;
import com.core.hamason.service.ICartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements ICartService {

    private final ICartRepository cartRepository;

    @Override
    public Optional<Cart> findByCustomer(Customer customer) {
        return cartRepository.findByCustomer(customer);
    }

    @Override
    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public void clearCart(Cart cart) {
        cart.getItems().clear();
        cartRepository.save(cart);
    }
}
