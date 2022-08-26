package com.haruhanjan.alcoholservice.entity;

import com.haruhanjan.alcoholservice.dto.ModifyAlcoholRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

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

    public void modify(ModifyAlcoholRequestDTO dto) {
        ofNullable(dto.getPrice()).ifPresent(p -> this.price = p);
        ofNullable(dto.getAlcoholType()).ifPresent(at -> this.alcoholType = at);
        ofNullable(dto.getName()).ifPresent(n -> this.name = n);
        ofNullable(dto.getProductDate()).ifPresent(pd -> this.productDate = pd);
        ofNullable(dto.getExpiryDate()).ifPresent(ed -> this.productDate = ed);
        ofNullable(dto.getAlcoholByVolume()).ifPresent(v -> this.alcoholByVolume = v);
        ofNullable(dto.getSeller()).ifPresent(s -> this.seller = s);
        ofNullable(dto.getMadeFrom()).ifPresent(mf -> this.madeFrom = mf);
        ofNullable(dto.getSugarDegree()).ifPresent(sd -> this.sugarDegree = sd);
        ofNullable(dto.getAcidDegree()).ifPresent(ad -> this.acidDegree = ad);
        ofNullable(dto.getIsSparkling()).ifPresent(is -> this.isSparkling = is);
        baseTimeEntity.update();
    }

    public void delete() {
        baseTimeEntity.delete();
    }
}
