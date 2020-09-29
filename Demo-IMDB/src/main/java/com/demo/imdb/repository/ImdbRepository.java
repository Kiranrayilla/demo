package com.demo.imdb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.imdb.model.IS;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@Repository
@SecurityRequirement(name = "bearer")
public interface ImdbRepository extends JpaRepository<IS, Long> {

	List<IS> findByLs(String ls);
}
