package com.core.hamason.serviceImpl;

import com.core.hamason.data.model.Customer;
import com.core.hamason.data.repository.ICustomerRepository;
import com.core.hamason.service.ICustomerService;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private ICustomerRepository customerRepository;

    @Override
    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> findByUsername(String username) {
        return customerRepository.findByUsername(username);
    }

    @Transactional
    @Override
    public void updateCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    @Transactional
    @Override
    public void deleteCustomer(String username) {
        Customer customer = customerRepository.findByUsername(username).orElse(null);
        if (customer != null) {
            customerRepository.delete(customer);
        }
    }
}
