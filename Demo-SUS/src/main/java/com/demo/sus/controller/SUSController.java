package com.demo.sus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.sus.model.SUS;
import com.demo.sus.model.SUSDetail;
import com.demo.sus.service.SUSService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/v1")
@RepositoryRestResource
@SecurityRequirement(name = "bearer")
public class SUSController {

	@Autowired
	private SUSService susService;

	@GetMapping("/sus")
	public ResponseEntity<List<SUS>> getAllsubscriber() {
		return ResponseEntity.ok().body(susService.getAllSUS());
	}

	@GetMapping("/sus/{id}")
	public ResponseEntity<SUS> getSubscriberById(@PathVariable long id) {
		return ResponseEntity.ok().body(susService.getSUSById(id));
	}

	@PostMapping("/sus")
	public ResponseEntity<SUS> createSubscriber(@RequestBody SUS sub) {
		return ResponseEntity.ok().body(this.susService.createSUS(sub));
	}

	@PutMapping("/sus/{id}")
	public ResponseEntity<SUS> updateSubscriber(@PathVariable long id, @RequestBody SUS sub) {
		sub.setId(id);
		return ResponseEntity.ok().body(this.susService.updateSUS(sub));
	}

	@DeleteMapping("/sus/{id}")
	public HttpStatus deleteSUSById(@PathVariable long id) {
		this.susService.deleteSUSById(id);
		return HttpStatus.OK;
	}

	@GetMapping("/findByLs/{ls}")
	public ResponseEntity<List<SUS>> findByLS(@PathVariable String ls) {
		return ResponseEntity.ok().body(susService.findByLs(ls));
	}

	@GetMapping("/findByLsFromImdb/{ls}")
	public ResponseEntity<List<SUSDetail>> findByLSFromImdb(@PathVariable String ls) {
		return ResponseEntity.ok().body(susService.findByLsFromImdb(ls));
	}
	
	@PostMapping("/updateStatus/")
	public ResponseEntity<String> updateStatus(@RequestBody SUS sus) {
		return ResponseEntity.ok().body(susService.updateStatus(sus));
	}
}
