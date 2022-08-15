package com.haruhanjan.alcoholservice.dto;

import com.haruhanjan.alcoholservice.entity.Alcohol;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
public class AlcoholRequest {

    @Getter
    private Long id;

    private int price;
    private String classification;

    public Alcohol toEntity(){
        return Alcohol.builder()
                .price(price)
                .classification(classification)
                .build();
    }
}
