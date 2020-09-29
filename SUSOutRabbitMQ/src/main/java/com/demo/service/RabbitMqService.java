package com.demo.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.model.SUSDetail;

@Service
public class RabbitMqService {
    @Autowired
    private AmqpTemplate amqpTemplate;

    private String EXCHANGE="otp-exchange";

    private String ROUTING_KEY="items";

    public void sendMessage(SUSDetail susDetail) {
        amqpTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, susDetail);
    }
}
