package com.haruhanjan.alcoholservice.entity;

import com.haruhanjan.alcoholservice.dto.AlcoholRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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


    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Double alcoholByVolume;
    private Integer sugarDegree;
    private Integer acidDegree;
    private Boolean isSparkling;
    @Column(nullable = false)
    private String madeFrom; // 원산지
    @Column(nullable = false)
    private String seller;

    @Column(nullable = false)
    private Integer price;
    @Column(nullable = false)
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
