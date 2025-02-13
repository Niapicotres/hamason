package com.core.hamason.data.repository;

import com.core.hamason.data.model.Order;
import com.core.hamason.data.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface IOrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCustomer(User customer);
}