package com.core.hamason.controllerImpl;

import com.core.hamason.data.model.Customer;
import com.core.hamason.service.ICustomerService;
import com.core.hamason.controller.ICustomerController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import java.util.List;
import java.util.Optional;

@Controller
public class CustomerControllerImpl implements ICustomerController {
	

    
    @Autowired
    private ICustomerService customerService ;

    
    
    public CustomerControllerImpl(ICustomerService customerService) {
        this.customerService = customerService;
    }
    
  
    @Override
    public Customer getCustomerById(Long id) {
        Optional<Customer> customerOpt = customerService.getCustomerById(id);
        return customerOpt.orElseThrow(() -> new RuntimeException("Customer not found with id: " + id)); // Lanza una excepción si no se encuentra el cliente
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
