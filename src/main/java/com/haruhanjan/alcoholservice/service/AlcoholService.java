package com.haruhanjan.alcoholservice.service;

import com.haruhanjan.alcoholservice.dto.AlcoholDto;
import com.haruhanjan.alcoholservice.entity.Alcohol;
import com.haruhanjan.alcoholservice.repository.AlcoholRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlcoholService {
    @Autowired
    private AlcoholRepository alcoholRepository;

    public void saveAlcohol(AlcoholDto dto) {
        Alcohol saved = Alcohol.builder()
                .classification(dto.getClassification())
                .price(dto.getPrice())
                .build();
        alcoholRepository.save(saved);
    }

    public void modify(AlcoholDto dto) {
        Alcohol target = alcoholRepository.findById(dto.getId()).orElse(null);
        if (target != null) {
            target.modify(dto);
        }
    }

    public void deleteAlcoholById(Long id) {
        if (id != null) {
            alcoholRepository.deleteById(id);
        }
    }

//    public List<AlcoholDto> getAllAlcohol() {
//        List<Alcohol> targets = alcoholRepository.findAll();
//        // 스프링처럼 짜고 싶어서 냅둠...
//    }
}
