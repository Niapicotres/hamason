package com.core.hamason.data.repository;

import com.core.hamason.data.model.User;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, String> {
    
	 @EntityGraph(attributePaths = "roleSet")
    // Encuentra usuario por username
    Optional<User> findByUsername(String username);

    // Elimina un usuario por username
    void deleteByUsername(String username);
    
    
}
