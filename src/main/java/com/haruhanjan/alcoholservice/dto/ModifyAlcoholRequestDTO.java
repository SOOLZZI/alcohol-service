package com.haruhanjan.alcoholservice.dto;

import com.haruhanjan.alcoholservice.entity.AlcoholType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ModifyAlcoholRequestDTO {
    private String name;
    private Double alcoholByVolume;
    private Integer sugarDegree;
    private Integer acidDegree;
    private Boolean isSparkling;
    private String madeFrom; // 원산지
    private String seller;
    private Integer price;
    private AlcoholType alcoholType;
    private LocalDate productDate;
    private LocalDate expiryDate;
}
