package com.core.hamason.data.repository;

import com.core.hamason.data.model.OrderLine;
import com.core.hamason.data.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrderLineRepository extends JpaRepository<OrderLine, Long> {
    List<OrderLine> findByOrder(Order order);
}
