package com.haruhanjan.alcoholservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AlcoholServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlcoholServiceApplication.class, args);
    }

}
