package com.haruhanjan.alcoholservice.repository;

import com.haruhanjan.alcoholservice.entity.Alcohol;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlcoholRepository extends JpaRepository<Alcohol, Long> {
    Page<Alcohol> findAll(Pageable pageable);
}
