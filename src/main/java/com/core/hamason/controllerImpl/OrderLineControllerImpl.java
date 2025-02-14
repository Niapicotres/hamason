package com.core.hamason.controllerImpl;

import com.core.hamason.controller.IOrderLineController;
import com.core.hamason.data.model.OrderLine;
import com.core.hamason.service.IOrderLineService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/order-lines")
public class OrderLineControllerImpl implements IOrderLineController {

    private final IOrderLineService orderLineService;

    @Override
    @GetMapping("/order/{orderId}")
    public String showOrderLines(@PathVariable Long orderId, Model model) {
        List<OrderLine> orderLines = orderLineService.findByOrderId(orderId);
        model.addAttribute("orderLines", orderLines);
        return "order-lines"; // Nombre del template Thymeleaf
    }

    @Override
    @GetMapping("/delete/{id}")
    public String deleteOrderLine(@PathVariable Long id) {
        orderLineService.delete(id);
        return "redirect:/order-lines"; // Redirige a la lista después de eliminar
    }
}
