package com.haruhanjan.alcoholservice.entity;

import com.haruhanjan.alcoholservice.dto.AlcoholRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Alcohol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alcohol_id")
    private Long id;

    @NotNull
    private String name;
    private Double alcoholByVolume;
    private Integer sugarDegree;
    private Integer acidDegree;
    private Boolean isSparkling;
    @NotNull
    private String madeFrom; // 원산지
    private String seller;

    @NotNull
    private Integer price;
    @NotNull
    @Enumerated(value = EnumType.STRING)
    private AlcoholType alcoholType;
    private LocalDate productDate;
    private LocalDate expiryDate;
    @Embedded
    @Builder.Default
    private BaseTimeEntity baseTimeEntity = new BaseTimeEntity();

    public void modify(AlcoholRequestDTO dto) {
        this.sugarDegree=dto.getSugarDegree();
        this.productDate=dto.getProductDate();
        this.acidDegree=dto.getAcidDegree();
        this.isSparkling=dto.getIsSparkling();
        this.madeFrom=dto.getMadeFrom();
        this.alcoholType=dto.getAlcoholType();
        this.expiryDate=dto.getExpiryDate();
        this.price=dto.getPrice();
        this.name=dto.getName();
        this.seller=dto.getSeller();
        this.alcoholByVolume=dto.getAlcoholByVolume();
        baseTimeEntity.update();
    }

    public void delete() {
        baseTimeEntity.delete();
    }
}
