package com.core.hamason.service;

import com.core.hamason.data.model.Customer;
import java.util.List;
import java.util.Optional;

public interface ICustomerService {
    List<Customer> findAllCustomers();
    Optional<Customer> findByUsername(String username);
    void updateCustomer(Customer customer);
    void deleteCustomer(String username);
}
