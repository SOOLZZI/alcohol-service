package com.haruhanjan.alcoholservice.service;

import com.haruhanjan.alcoholservice.dto.AlcoholDto;
import com.haruhanjan.alcoholservice.entity.Alcohol;
import com.haruhanjan.alcoholservice.repository.AlcoholRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
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

    @Transactional
    public void modify(AlcoholRequest dto) {
        Alcohol target = alcoholRepository.findById(dto.getId()).orElseThrow(EntityNotFoundException::new);
        target.modify(dto);
    }


    public void deleteById(Long id) {
        alcoholRepository.deleteById(id);
    }

    public List<AlcoholResponse> getAll() {
        List<Alcohol> targets = alcoholRepository.findAll();
        return AlcoholResponse.listOf(targets);
    }
}
