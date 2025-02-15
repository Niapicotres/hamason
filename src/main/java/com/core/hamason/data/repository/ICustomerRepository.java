package com.core.hamason.data.repository;

import com.core.hamason.data.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByEmail(String email);
}