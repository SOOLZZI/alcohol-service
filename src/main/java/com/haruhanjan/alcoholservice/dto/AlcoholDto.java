package com.haruhanjan.alcoholservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AlcoholDto {
    private Long id;
    private int price;
    private String classification;
    private LocalDateTime createdAt;
}
