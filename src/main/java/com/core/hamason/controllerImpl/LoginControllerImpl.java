package com.core.hamason.controllerImpl;

import com.core.hamason.controller.ILoginController;
import com.core.hamason.data.model.Login;
import com.core.hamason.service.IUserService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

import java.security.Principal;

@Slf4j
@Controller
public class LoginControllerImpl implements ILoginController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	
	
    @Override
    @GetMapping("/loginGet")
    public String loginGet(Principal principal, Model model, HttpServletRequest request) {
        log.info("TRAZA loginGet");
        model.addAttribute("login", new Login());
        return "standardLayouts/loginPage"; // Nombre de la plantilla HTML
    }
    
    
    @PostMapping("/loginPostE")
    public String loginPost(
            @Valid Login login,
            BindingResult bindingResult,
            HttpServletRequest request,
            Model model
    ) {
        log.info("TRAZA loginPost: {}", login);

        // Validación de errores en el formulario
        if (bindingResult.hasErrors()) {
            log.warn("VALIDATION ERRORS: {}", bindingResult.getAllErrors());
            return "standardLayouts/loginPage"; // vuelve al formulario
        }

        // 1. Crear token de autenticación
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword());

        // 2. Autenticar con el AuthenticationManager
        Authentication authentication = authenticationManager.authenticate(authToken);

        if (!authentication.isAuthenticated()) {
            log.info("BAD CREDENTIALS! NOT LOGGED");
            model.addAttribute("errorMsg", "Wrong username or password");
            return "standardLayouts/loginPage";
        }

  
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);

        HttpSession session = request.getSession(true);
        session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, securityContext);

      
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));

        boolean isEmployee = authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_EMPLOYEE"));
        log.info("ADMIN before"+authentication.getAuthorities().toString());
        if (isAdmin) {
        	 log.info("ADMIN LOGGED");
            // Si es ADMIN, redirige al panel admin
            return "admin/adminIndex";
        } else if (isEmployee) {
            // Si es EMPLOYEE, redirige a la vista de empleado
            return "admin/employeeIndex";
        } else {
            // Caso contrario (CUSTOMER u otro rol)
            return "redirect:/";  // Vuelve a la tienda principal
        }
    }

    
    
//    @Override
//	@PostMapping({"/loginPost"})
//	public String loginPost(
//			@Valid Login login,
//			BindingResult bindingResult,
//			HttpServletRequest request,
//			Model model) {
//		System.out.println("TRAZA loginPost");
//		if (bindingResult.hasErrors()) {
//			log.warn("VALIDATION ERRORS!!!: " + bindingResult.getAllErrors());
//			return "loginPage";
//		}
//		else {
//			UsernamePasswordAuthenticationToken authenticationToken =
//					new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword());
//			Authentication authentication = authenticationManager.authenticate(authenticationToken);
//			if (authentication.isAuthenticated()) {
//				SecurityContext securityContext = SecurityContextHolder.getContext();
//				securityContext.setAuthentication(authentication);
//				//
//				HttpSession session = request.getSession(true);
//				session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, securityContext);
//			} 
//			else {
//				log.info("BAD CREDENTIALS! NOT LOGGED");
//				return "loginPage";
//			}
//			
//		}
//		//SACAR LOS ROLES DEL USUARIO Y MANDARLO AL SU INDEX RELACIONADO
//		
//		return "/admin/adminIndex";
//	}
}
