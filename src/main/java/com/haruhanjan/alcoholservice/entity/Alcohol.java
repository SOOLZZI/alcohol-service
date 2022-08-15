package com.haruhanjan.alcoholservice.entity;

import com.haruhanjan.alcoholservice.dto.AlcoholDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.intellij.lang.annotations.Identifier;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Alcohol {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alcohol_id")
    private Long id;

    private int price;
    private String classification;
    private LocalDateTime createdAt;

    @Builder
    public Alcohol(int price, String classification) {
        this.price = price;
        this.classification = classification;
        this.createdAt = LocalDateTime.now();
    }

    public void modify(AlcoholDto dto) {
        this.classification = dto.getClassification();
        this.price = dto.getPrice();
    }
}
