package com.demo.is.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.is.model.IS;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@Repository
@SecurityRequirement(name = "bearer")
public interface ISRepository extends JpaRepository<IS, Long> {

	List<IS> findByLs(String ls);
}
