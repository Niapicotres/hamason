package com.core.hamason.data.repository;

import com.core.hamason.data.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface ICustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByUsername(String username);

    @Transactional
    void deleteByUsername(String username);
}
