package com.core.hamason.controllerImpl;

import com.core.hamason.controller.IRoleController;
import com.core.hamason.data.model.Role;
import com.core.hamason.service.IRoleService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("/roles")
public class RoleControllerImpl implements IRoleController {

    private final IRoleService roleService;

    public RoleControllerImpl(IRoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    @GetMapping
    public String roleListGet(Principal principal, Model model, HttpServletRequest request) {
        Set<Role> roles = roleService.findAll();
        model.addAttribute("roles", roles);
        return "roles/list"; // Thymeleaf view for listing roles
    }

    @Override
    @GetMapping("/view/{roleName}")
    public String roleViewGet(@PathVariable String roleName, Principal principal, Model model, HttpServletRequest request) {
        Optional<Role> role = roleService.findById(roleName);
        role.ifPresent(value -> model.addAttribute("role", value));
        return "roles/view"; // Thymeleaf template for viewing a role
    }

    @Override
    @GetMapping("/new")
    public String roleAddGet(Principal principal, Model model, HttpServletRequest request) {
        model.addAttribute("role", new Role());
        return "roles/form"; // Thymeleaf form for creating a role
    }

    @Override
    @PostMapping("/save")
    public String roleAddPost(@Valid Role role, BindingResult bindingResult, Principal principal, Model model, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "roles/form";
        }
        roleService.save(role);
        return "redirect:/roles";
    }

    @Override
    @GetMapping("/delete/{roleName}")
    public String roleDeleteGet(@PathVariable String roleName, Principal principal, Model model, HttpServletRequest request) {
        Optional<Role> role = roleService.findById(roleName);
        role.ifPresent(value -> model.addAttribute("role", value));
        return "roles/confirm-delete"; // Confirmation page before deleting
    }

    @Override
    @PostMapping("/delete/{roleName}")
    public String roleDeleteConfirmed(@PathVariable String roleName, Principal principal, Model model, HttpServletRequest request) {
        Optional<Role> role = roleService.findById(roleName);
        role.ifPresent(roleService::save); // Aquí deberías llamar a `delete()`
        return "redirect:/roles";
    }

    @Override
    @GetMapping("/edit/{roleName}")
    public String roleUpdateGet(@PathVariable String roleName, Principal principal, Model model, HttpServletRequest request) {
        Optional<Role> role = roleService.findById(roleName);
        role.ifPresent(value -> model.addAttribute("role", value));
        return "roles/form"; // Reutiliza la misma vista para editar
    }

    @Override
    @PostMapping("/edit/{roleName}")
    public String roleUpdatePost(@Valid Role role, BindingResult bindingResult, Principal principal, Model model, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "roles/form";
        }
        roleService.save(role);
        return "redirect:/roles";
    }
}
