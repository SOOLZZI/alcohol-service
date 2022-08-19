package com.haruhanjan.alcoholservice.entity;

import com.haruhanjan.alcoholservice.dto.ModifyAlcoholDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@DynamicInsert
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

    private String name;
    private Double volume; // 도수*10
    private String madeFrom; // 원산지
    private String seller;

    private Integer price;
    @Enumerated(value = EnumType.STRING)
    private AlcoholType alcoholType;
    private LocalDate productDate;


    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @ColumnDefault("0")
    private Boolean isDeleted;
    private LocalDateTime deletedAt;

    public void setCreatedAt() {
        this.createdAt = LocalDateTime.now();
    }

    public void setUpdatedAt() {
        this.updatedAt = LocalDateTime.now();
    }

    public void delete() {
        this.deletedAt = LocalDateTime.now();
        setUpdatedAt();
        this.isDeleted = true;
    }

    public void modify(ModifyAlcoholDTO dto) {
        Optional.ofNullable(dto.getPrice()).ifPresent(p -> price = p);
        Optional.ofNullable(dto.getAlcoholType()).ifPresent(p -> alcoholType = p);
        Optional.ofNullable(dto.getName()).ifPresent(p -> name = p);
        Optional.ofNullable(dto.getProductDate()).ifPresent(p -> productDate = p);
        Optional.ofNullable(dto.getVolume()).ifPresent(p -> volume = p);
        Optional.ofNullable(dto.getSeller()).ifPresent(p -> seller = p);
        Optional.ofNullable(dto.getMadeFrom()).ifPresent(p -> madeFrom = p);
        setUpdatedAt();
    }
}
