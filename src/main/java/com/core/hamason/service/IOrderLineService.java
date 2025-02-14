package com.core.hamason.service;

import com.core.hamason.data.model.OrderLine;

import java.util.List;
import java.util.Optional;

public interface IOrderLineService {
    OrderLine save(OrderLine orderLine);
    Optional<OrderLine> findById(Long id);
    List<OrderLine> findByOrderId(Long orderId);
    List<OrderLine> findAll();
    void delete(Long id);
}

