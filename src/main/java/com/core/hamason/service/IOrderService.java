package com.core.hamason.service;

import java.util.Set;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.core.hamason.data.model.Order;
import com.core.hamason.data.model.User;

@Service
public interface IOrderService {
    
    Order save(Order order);
    
    Optional<Order> findById(Long id);
    
    Set<Order> findAll();
    
    Set<Order> findByCustomer(User customer);
    
    void deleteById(Long id);
    
    Order newEntity();
    
    void updateStatus(Long id, String status);
}
