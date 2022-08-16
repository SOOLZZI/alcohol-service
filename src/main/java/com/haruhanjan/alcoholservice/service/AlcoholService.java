package com.haruhanjan.alcoholservice.service;

import com.haruhanjan.alcoholservice.dto.AlcoholDto;
import com.haruhanjan.alcoholservice.dto.AlcoholRequest;
import com.haruhanjan.alcoholservice.dto.AlcoholResponse;
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

    private final AlcoholRepository alcoholRepository; // field injection

    public AlcoholResponse save(AlcoholRequest dto) {

        Alcohol saved = alcoholRepository.save(dto.toEntity());
        return AlcoholResponse.of(saved);
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
