package com.haruhanjan.alcoholservice.dto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ModifyDto {
    private String name;
    private float originVolume;
    private int volume; // 도수*10
    private String madeFrom; // 원산지
    private String seller;
    private int price;
    private String alcoholType;
    private LocalDate productDate;
}
