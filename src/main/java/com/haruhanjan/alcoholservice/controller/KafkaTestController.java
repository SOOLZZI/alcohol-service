package com.haruhanjan.alcoholservice.controller;

import com.haruhanjan.alcoholservice.service.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class KafkaTestController {
    private final KafkaProducerService producerService;
    @GetMapping("api/test/kafka")
    public String hello() {
        producerService.sendMessage("asdf");
        return "ok";
    }
}
