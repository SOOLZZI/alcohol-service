package com.haruhanjan.alcoholservice.dto;

import com.haruhanjan.alcoholservice.entity.Alcohol;
import com.haruhanjan.alcoholservice.entity.AlcoholType;

import com.haruhanjan.alcoholservice.entity.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateAlcoholRequestDTO {

    private String name;

    @Getter
    private Integer price;
    private Double volume;
    private String madeFrom; // 원산지
    private String seller; // USER ID로 넣기 TODO
    private AlcoholType alcoholType;
    private LocalDate productDate;

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
