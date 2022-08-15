package com.haruhanjan.alcoholservice.dto;

import com.haruhanjan.alcoholservice.entity.Alcohol;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@AllArgsConstructor
@Getter
public class AlcoholResponse {
    private Long id;

    private int price;
    private String classification;
    private LocalDateTime createdAt;

    public static AlcoholResponse of(Alcohol alcohol) {
        return AlcoholResponse.builder()
                .id(alcohol.getId())
                .price(alcohol.getPrice())
                .classification(alcohol.getClassification())
                .createdAt(alcohol.getCreatedAt())
                .build();
    }

    public static List<AlcoholResponse> listOf(List<Alcohol> lists) {
        return lists.stream()
                .map(AlcoholResponse::of)
                .collect(Collectors.toList());
    }

}
