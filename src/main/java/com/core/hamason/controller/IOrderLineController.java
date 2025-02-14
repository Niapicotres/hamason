package com.core.hamason.controller;


public interface IOrderLineController {
    String showOrderLines(Long orderId, org.springframework.ui.Model model);
    String deleteOrderLine(Long id);
}
