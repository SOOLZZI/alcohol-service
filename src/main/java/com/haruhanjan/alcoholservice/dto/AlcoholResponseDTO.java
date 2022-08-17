package com.haruhanjan.alcoholservice.dto;

import com.haruhanjan.alcoholservice.entity.Alcohol;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@AllArgsConstructor
@Getter
public class AlcoholResponseDTO {
    // validation 적용
    private Long id;

    private String name;
    private float originVolume;
    private String madeFrom; // 원산지
    private String seller;
    private int price;
    private String alcoholType;
    private LocalDate productDate;

    public static AlcoholResponseDTO of(Alcohol alcohol) {
        return AlcoholResponseDTO.builder()
                .id(alcohol.getId())
                .name(alcohol.getName())
                .madeFrom(alcohol.getMadeFrom())
                .productDate(alcohol.getProductDate())
                .price(alcohol.getPrice())
                .seller(alcohol.getSeller())
                .originVolume((float) alcohol.getVolume()/10)
                .alcoholType(alcohol.getAlcoholType().toString())
                .build();
    }

    public static List<AlcoholResponseDTO> listOf(List<Alcohol> lists) {
        return lists.stream()
                .map(AlcoholResponseDTO::of)
                .collect(Collectors.toList());
    }

}
