package com.demo.sus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.sus.model.SUS;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@Repository
@SecurityRequirement(name = "bearer")
public interface SUSRepository extends JpaRepository<SUS, Long> {
	
	List<SUS> findByLs(String ls);
	
}
