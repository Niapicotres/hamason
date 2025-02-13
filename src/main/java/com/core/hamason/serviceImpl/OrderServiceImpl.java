package com.core.hamason.serviceImpl;
import com.core.hamason.data.model.Order;
import com.core.hamason.data.model.User;
import com.core.hamason.data.repository.IOrderRepository;
import com.core.hamason.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private IOrderRepository orderRepository;

    @Override
    public Order save(Order order) {
        order.setCreationDate(LocalDate.now());
        return orderRepository.save(order);
    }
    
    @Override
    public Order newEntity() {
        return new Order();  
    }

    @Override
    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public Set<Order> findAll() {
        return orderRepository.findAll().stream().collect(Collectors.toSet());
    }

    @Override
    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public Set<Order> findByCustomer(User customer) {
        List<Order> ordersList = orderRepository.findByCustomer(customer);
        return new HashSet<>(ordersList);  // Convertimos el List a un Set
    }
    
    
    @Override
    public void updateStatus(Long id, String status) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            order.setStatus(status);
            orderRepository.save(order);
        }
        
         
    }
}
