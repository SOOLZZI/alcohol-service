package com.haruhanjan.alcoholservice.entity;

import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;
import java.util.Optional;

@Embeddable
public class BaseTimeEntity {
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    public Boolean isDeleted(){
        return Optional.ofNullable(deletedAt).isPresent();
    }

    public BaseTimeEntity() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public void update(){
        this.updatedAt = LocalDateTime.now();
    }
    public void delete(){
        this.deletedAt = LocalDateTime.now();
    }
}
