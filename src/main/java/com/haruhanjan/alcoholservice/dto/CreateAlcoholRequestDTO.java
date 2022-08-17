package com.haruhanjan.alcoholservice.dto;

import com.haruhanjan.alcoholservice.entity.Alcohol;
import com.haruhanjan.alcoholservice.entity.AlcoholType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
public class CreateAlcoholRequestDTO {

    @Getter
    private Long id;
    private String name;
    private float originVolume;
    private int volume; // 도수*10
    private String madeFrom; // 원산지
    private String seller;
    private String alcoholType;
    private LocalDate productDate;
    @Getter
    private int price;

    public Alcohol toEntity(){
        return Alcohol.builder()
                .name(name)
                .price(price)
                .volume((int) (originVolume*10))
                .madeFrom(madeFrom)
                .seller(seller)
                .alcoholType(AlcoholType.valueOf(alcoholType))
                .productDate(productDate)
                .build();
    }
}
