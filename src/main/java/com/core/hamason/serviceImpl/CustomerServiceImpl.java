package com.core.hamason.serviceImpl;

import com.core.hamason.data.model.Customer;
import com.core.hamason.data.repository.ICustomerRepository;
import com.core.hamason.service.ICustomerService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private ICustomerRepository customerRepository;

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> getCustomerById(String username) {
        return customerRepository.findById(username);
    }

    @Override
    public Optional<Customer> getCustomerByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    @Transactional
    @Override
    public Customer saveCustomer(Customer customer) {
        log.info("Saving customer: " + customer.getUsername());
        return customerRepository.save(customer);
    }

    @Transactional
    @Override
    public void updateCustomer(Customer customer) {
        Optional<Customer> existingCustomer = customerRepository.findById(customer.getUsername());
        if (existingCustomer.isPresent()) {
            Customer updatedCustomer = existingCustomer.get();
            updatedCustomer.setShippingAddress(customer.getShippingAddress());
            updatedCustomer.setEmail(customer.getEmail());
            updatedCustomer.setPhone(customer.getPhone());
            updatedCustomer.setCreditCardNumber(customer.getCreditCardNumber());
            updatedCustomer.setCreditCardExpiry(customer.getCreditCardExpiry());

            log.info("Updating customer: " + customer.getUsername());
            customerRepository.save(updatedCustomer);
        } else {
            log.warn("Customer not found: " + customer.getUsername());
        }
    }
    
    @Transactional
    @Override
    public void deleteCustomer(String username) {
        log.info("Deleting customer: " + username);
        customerRepository.deleteById(username);
    }
}
