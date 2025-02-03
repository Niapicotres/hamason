package com.core.hamason.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.core.hamason.data.model.Status;

@Repository
public interface IStatusRepository extends JpaRepository<Status, Long> {

}
