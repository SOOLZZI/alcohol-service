package com.haruhanjan.alcoholservice.entity;

import com.haruhanjan.alcoholservice.dto.CreateRequestDTO;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@DynamicInsert
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

    @ColumnDefault("0")
    private Boolean isDeleted;
    private LocalDateTime deletedAt;

    public void setCreatedAt(){
        this.createdAt = LocalDateTime.now();
    }

    public void modify(CreateRequestDTO dto) {
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
