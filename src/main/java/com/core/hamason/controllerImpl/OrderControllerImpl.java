package com.core.hamason.controllerImpl;

import com.core.hamason.controller.IOrderController;
import com.core.hamason.data.model.Order;
import com.core.hamason.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/orders")
public class OrderControllerImpl implements IOrderController {

    @Autowired
    private IOrderService orderService;

    @Override
    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        Order savedOrder = orderService.save(order);
        return ResponseEntity.ok(savedOrder);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        return orderService.findById(id)
                .map(order -> ResponseEntity.ok(order))
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    @GetMapping
    public ResponseEntity<Set<Order>> getAllOrders() {
        Set<Order> orders = orderService.findAll();
        return ResponseEntity.ok(orders);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order order) {
        // Aquí extraemos el estado del pedido del objeto `order`
        String status = order.getStatus();
        
        // Luego actualizamos el estado del pedido con el servicio
        orderService.updateStatus(id, status);
        
        // Obtenemos la orden actualizada
        Order updatedOrder = orderService.findById(id).orElse(null);
        
        if (updatedOrder != null) {
            return ResponseEntity.ok(updatedOrder);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
