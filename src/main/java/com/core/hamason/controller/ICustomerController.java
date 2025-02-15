package com.core.hamason.controller;

import com.core.hamason.data.model.Customer;
import java.util.List;

public interface ICustomerController {
    Customer getCustomerById(Long id);
    Customer getCustomerByEmail(String email);
    List<Customer> getAllCustomers();
    Customer saveCustomer(Customer customer);
    void deleteCustomer(Long id);
}
