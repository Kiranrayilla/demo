package com.demo.controller;

import com.demo.model.SUSDetail;
import com.demo.service.RabbitMqService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RabbitMqController {
    @Autowired
    RabbitMqService rabbitMqService;

    @PostMapping("/susDetail")
    public ResponseEntity<String> postMessage(@RequestBody SUSDetail item){
        rabbitMqService.sendMessage(item);
        return new ResponseEntity<String>("SUS Details pushed to RabbitMQ",HttpStatus.CREATED);
    }
}
