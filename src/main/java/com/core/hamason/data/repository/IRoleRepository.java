package com.core.hamason.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.core.hamason.data.model.Role;

@Repository
public interface IRoleRepository extends JpaRepository<Role, String> {

}
