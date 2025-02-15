package com.core.hamason.controllerImpl;

import com.core.hamason.data.model.Customer;
import com.core.hamason.service.ICustomerService;
import com.core.hamason.controller.ICustomerController;
import org.springframework.stereotype.Controller;
import java.util.List;

@Controller
public class CustomerControllerImpl implements ICustomerController {
    private final ICustomerService customerService;

    public CustomerControllerImpl(ICustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public Customer getCustomerById(Long id) {
        return customerService.getCustomerById(id);
    }

    @Override
    public Customer getCustomerByEmail(String email) {
        return customerService.getCustomerByEmail(email);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        return customerService.saveCustomer(customer);
    }

    @Override
    public void deleteCustomer(Long id) {
        customerService.deleteCustomer(id);
    }
}
