package com.haruhanjan.alcoholservice.dto;

import com.haruhanjan.alcoholservice.entity.Alcohol;
import com.haruhanjan.alcoholservice.entity.AlcoholType;
import lombok.*;

import java.time.LocalDate;


@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreateAlcoholRequestDTO {
    private String name;
    private float originVolume;
    private String madeFrom; // 원산지
    private String seller;
    private AlcoholType alcoholType;
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
                .alcoholType(alcoholType)
                .productDate(productDate)
                .build();
    }
}
