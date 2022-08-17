package com.haruhanjan.alcoholservice.dto;

import com.haruhanjan.alcoholservice.entity.Alcohol;
import com.haruhanjan.alcoholservice.entity.AlcoholType;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@AllArgsConstructor
@Getter
public class AlcoholResponse {
    private Long id;

    private String name;
    private float originVolume;
    private String madeFrom; // 원산지
    private String seller;
    private int price;
    private String alcoholType;
    private LocalDate productDate;

    public static AlcoholResponse of(Alcohol alcohol) {
        return AlcoholResponse.builder()
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

    public static List<AlcoholResponse> listOf(List<Alcohol> lists) {
        return lists.stream()
                .map(AlcoholResponse::of)
                .collect(Collectors.toList());
    }

}
