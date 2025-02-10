package com.core.hamason;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.core.hamason.data.model.Role;
import com.core.hamason.data.model.User;
import com.core.hamason.data.repository.IRoleRepository;
import com.core.hamason.data.repository.IFamilyCategoryRepository;
import com.core.hamason.data.repository.IUserRepository;

@SpringBootApplication
public class HamasonApplication {

	public static void main(String[] args) {
		SpringApplication.run(HamasonApplication.class, args);
	}
	
	@Bean
	CommandLineRunner runner(
		IUserRepository userRepository,
		IRoleRepository roleRepository,
		IFamilyCategoryRepository statusRepository

			) 
	{ return args -> {
		roleRepository.save(new Role("ADMIN"));
		roleRepository.save(new Role("USER"));
		roleRepository.save(new Role("MANAGER"));

		userRepository.save(new User("ana", "ana@gmail.com", "$2a$12$LLE1DtYpn9la045NGdITZebqhtqMDtexqlLct0SE0cHuE9MtqN7Yu" /*anaPass*/, "Ana Perez", 
				LocalDate.now().plusMonths(1), LocalDate.now().plusMonths(1), 
				true, false, Set.of(roleRepository.findById("ADMIN").get(),
						roleRepository.findById("MANAGER").get()
						)));
		userRepository.save(new User("luis", "luis@gmail.com", "$2a$12$GRfg5oZ1Dh3VaCR1VLit1eh6hTXC0FzL1PRJADPs9.mcCCokyMDje" /*"luisPass"*/, "Luis Sanchez", 
				LocalDate.now().plusMonths(1), LocalDate.now().plusMonths(1), 
				true, false, Set.of(roleRepository.findById("USER").get()
						)));
		userRepository.save(new User("eva", "eva@gmail.com", "$2a$12$tSThCftgzCyejVRUjvqJa.5ibrmZRgXvmjElfx5idN2tjgeTN04pC" /*"evaPass"*/, "Eva Gomez", 
				LocalDate.now().plusMonths(1), LocalDate.now().plusMonths(1), 
				true, false, Set.of(roleRepository.findById("ADMIN").get()
						)));
	
//		statusRepository.save(new Status(null, "INICIADO"));
//		statusRepository.save(new Status(null, "RECHAZADO"));
//		statusRepository.save(new Status(null, "ENVIADO"));
//		statusRepository.save(new Status(null, "ACEPTADO"));
		};
	}

}
