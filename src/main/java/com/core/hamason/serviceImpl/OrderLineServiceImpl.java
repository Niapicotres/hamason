package com.core.hamason.serviceImpl;

import com.core.hamason.data.model.Order;
import com.core.hamason.data.model.OrderLine;
import com.core.hamason.data.repository.IOrderLineRepository;
import com.core.hamason.service.IOrderLineService;
import com.core.hamason.service.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderLineServiceImpl implements IOrderLineService {

    private final IOrderLineRepository orderLineRepository;
    private final IOrderService orderService;

    @Override
    public OrderLine save(OrderLine orderLine) {
        return orderLineRepository.save(orderLine);
    }

    @Override
    public Optional<OrderLine> findById(Long id) {
        return orderLineRepository.findById(id);
    }

    @Override
    public List<OrderLine> findByOrderId(Long orderId) {
        Optional<Order> order = orderService.getOrderById(orderId);
        return order.map(orderLineRepository::findByOrder).orElse(List.of());
    }

    @Override
    public List<OrderLine> findAll() {
        return orderLineRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        orderLineRepository.deleteById(id);
    }
}
