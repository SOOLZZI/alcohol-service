package com.haruhanjan.alcoholservice.entity;

import com.haruhanjan.alcoholservice.dto.ModifyAlcoholDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

import static java.util.Optional.ofNullable;

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
    private Double volume;
    private String madeFrom; // 원산지
    private String seller;

    private Integer price;
    @Enumerated(value = EnumType.STRING)
    private AlcoholType alcoholType;
    private LocalDate productDate;
    @Embedded
    @Builder.Default
    private BaseTimeEntity baseTimeEntity = new BaseTimeEntity();

    public void modify(ModifyAlcoholDTO dto) {
        ofNullable(dto.getPrice()).ifPresent(p -> this.price = p);
        ofNullable(dto.getAlcoholType()).ifPresent(at -> this.alcoholType = at);
        ofNullable(dto.getName()).ifPresent(n -> this.name = n);
        ofNullable(dto.getProductDate()).ifPresent(pd -> this.productDate = pd);
        ofNullable(dto.getVolume()).ifPresent(v -> this.volume = v);
        ofNullable(dto.getSeller()).ifPresent(s -> this.seller = s);
        ofNullable(dto.getMadeFrom()).ifPresent(mf -> this.madeFrom = mf);
        baseTimeEntity.update();
    }

    public void delete() {
        baseTimeEntity.delete();
    }
}
