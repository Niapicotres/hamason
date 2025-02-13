package com.core.hamason.controllerImpl;

import com.core.hamason.controller.IOrderLineController;
import com.core.hamason.data.model.OrderLine;
import com.core.hamason.service.IOrderLineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class OrderLineControllerImpl implements IOrderLineController {

    private final IOrderLineService orderLineService;

    @Override
    public ResponseEntity<OrderLine> createOrderLine(OrderLine orderLine) {
        OrderLine savedOrderLine = orderLineService.save(orderLine);
        return ResponseEntity.ok(savedOrderLine);
    }

    @Override
    public ResponseEntity<OrderLine> getOrderLineById(Long id) {
        Optional<OrderLine> orderLine = orderLineService.findById(id);
        return orderLine.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<List<OrderLine>> getOrderLinesByOrderId(Long orderId) {
        List<OrderLine> orderLines = orderLineService.findByOrderId(orderId);
        return ResponseEntity.ok(orderLines);
    }

    @Override
    public ResponseEntity<List<OrderLine>> getAllOrderLines() {
        return ResponseEntity.ok(orderLineService.findAll());
    }

    @Override
    public ResponseEntity<Void> deleteOrderLine(Long id) {
        orderLineService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
