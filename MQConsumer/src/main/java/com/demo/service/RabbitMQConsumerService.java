package com.demo.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.demo.model.SUSDetail;

@Service
public class RabbitMQConsumerService {
    private static final String QUEUE="SUS.IN";
    private SUSDetail data;
    
    @RabbitListener(queues = QUEUE)
    public void receiveMessage(SUSDetail susDetail) {
        System.out.println("Received Message from SUS.IN Queue >>"+susDetail);
        this.data = susDetail;
    }
    
    public SUSDetail getData() {
    	return this.data;
    }
}
