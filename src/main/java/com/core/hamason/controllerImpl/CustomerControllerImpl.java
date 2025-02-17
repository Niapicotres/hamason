package com.core.hamason.controllerImpl;

import com.core.hamason.controller.ICustomerController;
import com.core.hamason.data.model.Customer;
import com.core.hamason.service.ICustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/admin/customers")
public class CustomerControllerImpl implements ICustomerController {

    @Autowired
    private ICustomerService customerService;

    @GetMapping
    @Override
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{username}")
    @Override
    public String getCustomerById(@PathVariable String username, Model model) {
        Customer customer = customerService.getCustomerById(username)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        model.addAttribute("customer", customer);
        return "admin/editCustomer";
    }

    @PostMapping("/save")
    @Override
    public String saveCustomer(@ModelAttribute Customer customer) {
        customerService.saveCustomer(customer);
        return "redirect:/admin/customers";
    }

    @GetMapping("/delete/{username}")
    @Override
    public String deleteCustomer(@PathVariable String username) {
        customerService.deleteCustomer(username);
        return "redirect:/admin/customers";
    }
}
