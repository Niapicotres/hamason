package com.core.hamason;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.core.hamason.data.model.FamilyCategory;
import com.core.hamason.data.model.Product;
import com.core.hamason.data.model.Role;
import com.core.hamason.data.model.User;

import com.core.hamason.data.repository.IRoleRepository;
import com.core.hamason.data.repository.IFamilyCategoryRepository;
import com.core.hamason.data.repository.IProductRepository;
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
		IFamilyCategoryRepository familyCategoryRepository,
		IProductRepository productRepository  
			) 
	{ return args -> {
	
	
		
		  
		//BEFORE INSERT DATA PRODUCTS SAVE CATEGORY
		    FamilyCategory food = familyCategoryRepository.save(new FamilyCategory(null, "FOOD", null));
	        FamilyCategory electronics = familyCategoryRepository.save(new FamilyCategory(null, "ELECTRONICS", null));
	        FamilyCategory textile = familyCategoryRepository.save(new FamilyCategory(null, "TEXTILE", null));
	        FamilyCategory jewelry = familyCategoryRepository.save(new FamilyCategory(null, "JEWELRY", null));
	        FamilyCategory perfumery = familyCategoryRepository.save(new FamilyCategory(null, "PERFUMERY", null));

		//DATA PRODUCTS
	      //FOOD
	        productRepository.save(Product.builder()
	                .description("Manzana Roja")
	                .imageUrl("manzana.jpg")
	                .price(new BigDecimal("1.50"))
	                .stock(100)
	                .rating(4)
	                .discount(new BigDecimal("0.00"))
	                .familyCategory(food)
	                .build());

	            productRepository.save(Product.builder()
	                .description("Pan Integral")
	                .imageUrl("pan.jpg")
	                .price(new BigDecimal("2.00"))
	                .stock(80)
	                .rating(5)
	                .discount(new BigDecimal("10.00"))
	                .familyCategory(food)
	                .build());

	            productRepository.save(Product.builder()
	                .description("Leche Descremada")
	                .imageUrl("leche.jpg")
	                .price(new BigDecimal("1.80"))
	                .stock(60)
	                .rating(4)
	                .discount(new BigDecimal("5.00"))
	                .familyCategory(food)
	                .build());
	            
	          //ELECTRONICS
	            productRepository.save(Product.builder()
	                .description("Auriculares Inalámbricos")
	                .imageUrl("auriculares.jpg")
	                .price(new BigDecimal("45.00"))
	                .stock(30)
	                .rating(5)
	                .discount(new BigDecimal("15.00"))
	                .familyCategory(electronics)
	                .build());

	            productRepository.save(Product.builder()
	                .description("Smartwatch Deportivo")
	                .imageUrl("smartwatch.jpg")
	                .price(new BigDecimal("60.00"))
	                .stock(40)
	                .rating(4)
	                .discount(new BigDecimal("10.00"))
	                .familyCategory(electronics)
	                .build());

	            productRepository.save(Product.builder()
	                .description("Cámara de Seguridad")
	                .imageUrl("camara.jpg")
	                .price(new BigDecimal("120.00"))
	                .stock(20)
	                .rating(5)
	                .discount(new BigDecimal("20.00"))
	                .familyCategory(electronics)
	                .build());
	            //TEXTILE
	            productRepository.save(Product.builder()
	                .description("Camiseta Deportiva")
	                .imageUrl("camiseta.jpg")
	                .price(new BigDecimal("25.00"))
	                .stock(50)
	                .rating(3)
	                .discount(new BigDecimal("0.00"))
	                .familyCategory(textile)
	                .build());

	            productRepository.save(Product.builder()
	                .description("Pantalón Vaquero")
	                .imageUrl("pantalon.jpg")
	                .price(new BigDecimal("40.00"))
	                .stock(35)
	                .rating(4)
	                .discount(new BigDecimal("5.00"))
	                .familyCategory(textile)
	                .build());

	            productRepository.save(Product.builder()
	                .description("Chaqueta de Cuero")
	                .imageUrl("chaqueta.jpg")
	                .price(new BigDecimal("90.00"))
	                .stock(15)
	                .rating(5)
	                .discount(new BigDecimal("10.00"))
	                .familyCategory(textile)
	                .build());
	          //JEWERLY
	            productRepository.save(Product.builder()
	                .description("Anillo de Oro")
	                .imageUrl("anillo.jpg")
	                .price(new BigDecimal("200.00"))
	                .stock(10)
	                .rating(5)
	                .discount(new BigDecimal("0.00"))
	                .familyCategory(jewelry)
	                .build());

	            productRepository.save(Product.builder()
	                .description("Collar de Plata")
	                .imageUrl("collar.jpg")
	                .price(new BigDecimal("80.00"))
	                .stock(20)
	                .rating(4)
	                .discount(new BigDecimal("5.00"))
	                .familyCategory(jewelry)
	                .build());

	            productRepository.save(Product.builder()
	                .description("Reloj de Lujo")
	                .imageUrl("reloj.jpg")
	                .price(new BigDecimal("500.00"))
	                .stock(5)
	                .rating(5)
	                .discount(new BigDecimal("15.00"))
	                .familyCategory(jewelry)
	                .build());
	            
	          //PERFUMERY
	            productRepository.save(Product.builder()
	                .description("Perfume Floral")
	                .imageUrl("perfume_floral.jpg")
	                .price(new BigDecimal("50.00"))
	                .stock(25)
	                .rating(4)
	                .discount(new BigDecimal("10.00"))
	                .familyCategory(perfumery)
	                .build());

	            productRepository.save(Product.builder()
	                .description("Colonia Masculina")
	                .imageUrl("colonia.jpg")
	                .price(new BigDecimal("45.00"))
	                .stock(30)
	                .rating(5)
	                .discount(new BigDecimal("5.00"))
	                .familyCategory(perfumery)
	                .build());

	            productRepository.save(Product.builder()
	                .description("Esencia de Lavanda")
	                .imageUrl("lavanda.jpg")
	                .price(new BigDecimal("30.00"))
	                .stock(40)
	                .rating(4)
	                .discount(new BigDecimal("0.00"))
	                .familyCategory(perfumery)
	                .build());
		};
		
		
	}

}
