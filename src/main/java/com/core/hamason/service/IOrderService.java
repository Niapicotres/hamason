package com.core.hamason.service;

import com.core.hamason.data.model.Order;
import com.core.hamason.data.model.User;
import java.util.List;
import java.util.Optional;

public interface IOrderService {
    List<Order> getAllOrders();
    Optional<Order> getOrderById(Long id);
    Order saveOrder(Order order);
    void deleteOrder(Long id);
    List<Order> getOrdersByCustomer(User customer);
}
