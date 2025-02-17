package com.core.hamason.controllerImpl;

import com.core.hamason.controller.IAdminController;
import com.core.hamason.data.model.Customer;
import com.core.hamason.data.model.User;
import com.core.hamason.service.ICustomerService;
import com.core.hamason.service.IProductService;
import com.core.hamason.service.IUserService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminControllerImpl implements IAdminController {

    @Autowired
    private IUserService userService;
    
    @Autowired
    private ICustomerService customerService;
    
    @Autowired
    private IProductService productService;


    /**
     * Admin Index: Muestra la lista de Users o Customers según la vista solicitada
     */
    @GetMapping("/adminIndex")
    public String adminIndex(@RequestParam(value = "view", required = false) String view, Model model) {

        if ("users".equals(view)) {
            model.addAttribute("userList", userService.findAllUsers());
            model.addAttribute("showUsers", true);
        } else if ("customers".equals(view)) {
            model.addAttribute("customerList", customerService.getAllCustomers());
            model.addAttribute("showCustomers", true);
        }

        return "admin/adminIndex";
    }

    /**
     * Editar Usuario
     */
    @GetMapping("/editUser")
    public String editUser(@RequestParam("username") String username, Model model) {
        User user = userService.findByUsername(username).orElse(new User());
        model.addAttribute("user", user);
        return "admin/editUsers"; 
    }

    @PostMapping("/updateUser")
    public String updateUser(@RequestParam("username") String username,
                             @RequestParam("fullname") String fullname,
                             @RequestParam("email") String email,
                             @RequestParam(value = "enabled", required = false) Boolean enabled) {
        User user = userService.findByUsername(username).orElse(null);
        if (user != null) {
            user.setFullname(fullname);
            user.setEmail(email);
            user.setEnabled(enabled != null); 
            userService.updateUser(user);
        }
        return "redirect:/admin/adminIndex?view=users";
    }

    @GetMapping("/deleteUser")
    public String deleteUser(@RequestParam("username") String username) {
        userService.deleteUser(username);
        return "redirect:/admin/adminIndex?view=users";
    }

    // ----------------------------------------
    // CRUD de Customers
    // ----------------------------------------

    /**
     * Editar Cliente
     */
    @GetMapping("/editCustomer")
    public String editCustomer(@RequestParam("username") String username, Model model) {
        Customer customer = customerService.getCustomerById(username).orElse(new Customer());
        model.addAttribute("customer", customer);
        return "admin/editCustomers"; 
    }

    /**
     * Actualizar Cliente
     */
    @PostMapping("/updateCustomer")
    public String updateCustomer(@RequestParam("username") String username,
                                 @RequestParam("shippingAddress") String shippingAddress,
                                 @RequestParam("email") String email,
                                 @RequestParam("phone") String phone,
                                 @RequestParam("creditCardNumber") String creditCardNumber,
                                 @RequestParam("creditCardExpiry") String creditCardExpiry) {
        Customer customer = customerService.getCustomerById(username).orElse(null);
        if (customer != null) {
            customer.setShippingAddress(shippingAddress);
            customer.setEmail(email);
            customer.setPhone(phone);
            customer.setCreditCardNumber(creditCardNumber);
            customer.setCreditCardExpiry(creditCardExpiry);
            customerService.updateCustomer(customer);
        }
        return "redirect:/admin/adminIndex?view=customers";
    }

    /**
     * Eliminar Cliente
     */
    @GetMapping("/deleteCustomer")
    public String deleteCustomer(@RequestParam("username") String username) {
        customerService.deleteCustomer(username);
        return "redirect:/admin/adminIndex?view=customers";
    }
}
