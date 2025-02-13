package com.core.hamason.controller;

import com.core.hamason.data.model.OrderLine;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/order-lines")
public interface IOrderLineController {

    @PostMapping
    ResponseEntity<OrderLine> createOrderLine(@RequestBody OrderLine orderLine);

    @GetMapping("/{id}")
    ResponseEntity<OrderLine> getOrderLineById(@PathVariable Long id);

    @GetMapping("/order/{orderId}")
    ResponseEntity<List<OrderLine>> getOrderLinesByOrderId(@PathVariable Long orderId);

    @GetMapping
    ResponseEntity<List<OrderLine>> getAllOrderLines();

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteOrderLine(@PathVariable Long id);
}
