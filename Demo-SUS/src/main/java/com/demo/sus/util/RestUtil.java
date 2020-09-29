package com.demo.sus.util;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import com.demo.sus.exception.ResourceNotFoundException;
import com.demo.sus.model.SUSDetail;

@Component
@SecurityRequirement(name = "bearer")
public class RestUtil {
	public static void main(String[] args) {
		RestUtil restTemplate = new RestUtil();
		String host = "localhost";
		String port = "8082";
		String resource = "/api/v1/dbenrich/ls/";
		restTemplate.fetchRestData(host, port, resource, "LS111111");
	}

	public List<SUSDetail> fetchRestData(String host, String port, String resource, String ls) {
		String isUrl = "http://" + host + ":" + port + resource + ls;
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.exchange(isUrl, HttpMethod.GET, entity, String.class);
		System.out.println("Response Code is " + response.getStatusCode());
		if (response.getStatusCode() == HttpStatus.OK) {
			ObjectMapper objectMapper = new ObjectMapper();
			List<SUSDetail> is;
			try {
				is = objectMapper.readValue(response.getBody(), new TypeReference<List<SUSDetail>>() {
				});
				return is;
			} catch (JsonParseException e) {
				throw new ResourceNotFoundException("Exception at : " + e.getLocalizedMessage());

			} catch (JsonMappingException e) {
				throw new ResourceNotFoundException("Exception at : " + e.getLocalizedMessage());
			} catch (IOException e) {
				throw new ResourceNotFoundException("Exception at : " + e.getLocalizedMessage());
			}

		} else {
			throw new ResourceNotFoundException("Record not found with id : " + ls);
		}
	}
}
