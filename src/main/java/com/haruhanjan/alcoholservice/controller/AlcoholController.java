package com.haruhanjan.alcoholservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AlcoholController {

    @GetMapping("/")
    public String hello() {
        return "hello";
    }
}
