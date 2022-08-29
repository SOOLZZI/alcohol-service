package com.haruhanjan.alcoholservice.dto;

import com.haruhanjan.alcoholservice.entity.Alcohol;
import com.haruhanjan.alcoholservice.entity.AlcoholType;
import lombok.*;
import org.jetbrains.annotations.NotNull;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlcoholRequestDTO {

    @NotBlank
    private String name;

    @Getter
    @NotNull
    private Integer price;
    @NotNull
    @Min(0)
    @Max(100)
    private Double alcoholByVolume;
    @Min(0)
    @Max(5)
    private Integer sugarDegree;
    @Min(0)
    @Max(5)
    private Integer acidDegree;
    private Boolean isSparkling;
    @NotNull
    private String madeFrom; // 원산지
    @NotNull
    private String seller; // USER ID로 넣기 TODO
    @NotNull
    private AlcoholType alcoholType;

    private LocalDate productDate;
    private LocalDate expiryDate;


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
                .expiryDate(expiryDate)
                .build();
    }
}
