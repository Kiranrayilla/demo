package com.demo.imdb.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@ResponseStatus
@SecurityRequirement(name = "bearer")
public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(String message) {
		super(message);
	}

	public ResourceNotFoundException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
