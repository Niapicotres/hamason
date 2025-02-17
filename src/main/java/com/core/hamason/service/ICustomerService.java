package com.core.hamason.service;

import com.core.hamason.data.model.Customer;
import java.util.List;
import java.util.Optional;

public interface ICustomerService {
    List<Customer> getAllCustomers();
    Optional<Customer> getCustomerById(String username);
    Optional<Customer> getCustomerByEmail(String email);
    Customer saveCustomer(Customer customer);
    void updateCustomer(Customer customer); 
    void deleteCustomer(String username);
}
