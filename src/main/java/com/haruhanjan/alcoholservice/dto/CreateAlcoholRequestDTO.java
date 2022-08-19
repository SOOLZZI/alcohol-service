package com.haruhanjan.alcoholservice.dto;

import com.haruhanjan.alcoholservice.entity.Alcohol;
import com.haruhanjan.alcoholservice.entity.AlcoholType;

import com.haruhanjan.alcoholservice.entity.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@AllArgsConstructor
public class CreateAlcoholRequestDTO {

    private String name;
    private Double volume;
    private String madeFrom; // 원산지
    private String seller; // USER ID로 넣기 TODO
    private AlcoholType alcoholType;
    private LocalDate productDate;
    @Getter
    private Integer price;

    public Alcohol toEntity(){
        return Alcohol.builder()
                .name(name)
                .price(price)
                .volume(volume)
                .madeFrom(madeFrom)
                .seller(seller)
                .alcoholType(alcoholType)
                .productDate(productDate)
                .baseTimeEntity(new BaseTimeEntity())
                .build();
    }
}
