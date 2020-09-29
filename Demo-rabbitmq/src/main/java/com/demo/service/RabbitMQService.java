package com.demo.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.demo.model.SUSDetail;

import net.minidev.json.JSONObject;

@Service
public class RabbitMQService {
	private static final String QUEUE = "SUS.IN";

	private String EXCHANGE = "otp-exchange";

	private String ROUTING_KEY = "items";

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private AmqpTemplate amqpTemplate;

	@RabbitListener(queues = QUEUE)
	public void receiveMessage(SUSDetail susDetail) {
		System.out.println("Received Message from SUS.IN Queue >>" + susDetail);
		String uri = "http://localhost:8088/susDetail";
		JSONObject json = new JSONObject();
		json.put("ls",susDetail.getLs());
		json.put("msisdn", susDetail.getMsisdn());
		
		SUSDetail result = restTemplate.postForObject(uri, json, SUSDetail.class);
		System.out.println("Result is : " + result);
		
	}

	/*
	 * public void sendMessage(SUSDetail item) {
	 * amqpTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, item); }
	 */

}
