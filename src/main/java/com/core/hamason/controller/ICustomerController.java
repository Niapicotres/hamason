package com.core.hamason.controller;

import com.core.hamason.data.model.Customer;
import org.springframework.ui.Model;
import java.util.List;

public interface ICustomerController {
    List<Customer> getAllCustomers();
    String getCustomerById(String username, Model model);
    String saveCustomer(Customer customer);
    String deleteCustomer(String username);
}
