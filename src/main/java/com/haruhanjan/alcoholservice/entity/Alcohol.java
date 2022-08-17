package com.haruhanjan.alcoholservice.entity;

import com.haruhanjan.alcoholservice.dto.AlcoholRequest;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Alcohol {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alcohol_id")
    private Long id;

    @Setter
    private String name;
    @Setter
    private int volume; // 도수*10
    @Setter
    private String madeFrom; // 원산지
    @Setter
    private String seller;

    @Setter
    private int price;
    @Setter
    @Enumerated(value = EnumType.STRING)
    private AlcoholType alcoholType;
    @Setter
    private LocalDate productDate;


    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private Boolean isDeleted = false;
    private LocalDateTime deletedAt;

    public Alcohol(String name, int volume, String madeFrom, String seller, int price, AlcoholType alcoholType, LocalDate productDate) {
        this.name = name;
        this.volume = volume;
        this.madeFrom = madeFrom;
        this.seller = seller;
        this.price = price;
        this.alcoholType = alcoholType;
        this.productDate = productDate;
        this.createdAt = LocalDateTime.now();
    }

    public void modify(AlcoholRequest dto) {
        this.price = dto.getPrice();
        this.updatedAt = LocalDateTime.now();
    }

    public void setUpdatedAt() {
        this.updatedAt = LocalDateTime.now();
    }

    public void delete() {
        this.deletedAt = LocalDateTime.now();
        setUpdatedAt();
        this.isDeleted = true;
    }
}
