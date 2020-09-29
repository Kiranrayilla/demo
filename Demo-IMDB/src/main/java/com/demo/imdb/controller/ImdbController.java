package com.demo.imdb.controller;

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

import com.demo.imdb.model.IS;
import com.demo.imdb.service.ImdbService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/v1")
@RepositoryRestResource
@SecurityRequirement(name = "bearer")
public class ImdbController {

	@Autowired
	private ImdbService isService;

	@GetMapping("/dbenrich") public ResponseEntity<List<IS>> getAllsubscriber() {
		return ResponseEntity.ok().body(isService.getAllImdb()); }


	@GetMapping("/dbenrich/{id}")
	public ResponseEntity<IS> getSubscriberById(@PathVariable long id) {
		return ResponseEntity.ok().body(isService.getIsByImdbId(id));
	}

	@PostMapping("/dbenrich")
	public ResponseEntity<IS> createSubscriber(@RequestBody IS is) {
		return ResponseEntity.ok().body(this.isService.createImdb(is));
	}

	@PutMapping("/dbenrich/{id}")
	public ResponseEntity<IS> updateSubscriber(@PathVariable long id, @RequestBody IS is) {
		is.setId(id);
		return ResponseEntity.ok().body(this.isService.updateImdb(is));
	}

	@DeleteMapping("/dbenrich/{id}")
	public HttpStatus deleteSubscriber(@PathVariable long id) {
		this.isService.deleteIS(id);
		return HttpStatus.OK;
	}

	@GetMapping("/dbenrich/ls/{ls}")
	public ResponseEntity<List<IS>> findByLS(@PathVariable String ls) {
		return ResponseEntity.ok().body(isService.findByLs(ls));
	}

}
