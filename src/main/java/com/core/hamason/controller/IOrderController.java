package com.core.hamason.controller;

import com.core.hamason.data.model.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Set;

@RequestMapping("/orders")
public interface IOrderController {

    @PostMapping
    ResponseEntity<Order> createOrder(@RequestBody Order order);

    @GetMapping("/{id}")
    ResponseEntity<Order> getOrderById(@PathVariable Long id);

    @GetMapping
    ResponseEntity<Set<Order>> getAllOrders();

    @PutMapping("/{id}")
    ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order order);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteOrder(@PathVariable Long id);
}
