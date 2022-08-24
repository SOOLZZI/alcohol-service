package com.haruhanjan.alcoholservice.dto;

import com.haruhanjan.alcoholservice.entity.Alcohol;
import com.haruhanjan.alcoholservice.entity.AlcoholType;
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
    private Double alcoholByVolume;
    private Integer sugarDegree;
    private Integer acidDegree;
    private Boolean isSparkling;
    private String madeFrom; // 원산지
    private String seller; // USER ID로 넣기 TODO
    private AlcoholType alcoholType;
    private LocalDate productDate;

    public Alcohol toEntity(){
        return Alcohol.builder()
                .name(name)
                .price(price)
                .alcoholByVolume(alcoholByVolume)
                .sugarDegree(sugarDegree)
                .acidDegree(acidDegree)
                .isSparkling(isSparkling)
                .madeFrom(madeFrom)
                .seller(seller)
                .alcoholType(alcoholType)
                .productDate(productDate)
                .build();
    }
}
