package com.haruhanjan.alcoholservice.dto;

import com.haruhanjan.alcoholservice.entity.Alcohol;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Getter
public class AlcoholResponseDTO {
    private Long id;

    private String name;
    private Double alcoholByVolume;
    private Integer sugarDegree;
    private Integer acidDegree;
    private Boolean isSparkling;
    private String madeFrom; // 원산지
    private String seller;
    private Integer price;
    private String alcoholType;
    private Integer storagePeriod;


    public AlcoholResponseDTO(Alcohol entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.alcoholByVolume = entity.getAlcoholByVolume();
        this.acidDegree = entity.getAcidDegree();
        this.isSparkling = entity.getIsSparkling();
        this.sugarDegree = entity.getSugarDegree();
        this.madeFrom = entity.getMadeFrom();
        this.seller = entity.getSeller();
        this.price = entity.getPrice();
        this.alcoholType = entity.getAlcoholType().name();
        this.storagePeriod = entity.getStoragePeriod();
    }
}
