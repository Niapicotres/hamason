package com.core.hamason.service;

import com.core.hamason.data.model.Customer;
import java.util.List;
import java.util.Optional;

public interface ICustomerService {
	Optional<Customer> getCustomerById(Long id); 
    Customer getCustomerByEmail(String email);
    List<Customer> getAllCustomers();
    Customer saveCustomer(Customer customer);
    void deleteCustomer(Long id);
}