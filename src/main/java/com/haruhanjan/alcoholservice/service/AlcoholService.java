package com.haruhanjan.alcoholservice.service;

import com.haruhanjan.alcoholservice.dto.AlcoholResponseDTO;
import com.haruhanjan.alcoholservice.dto.AlcoholRequestDTO;
import com.haruhanjan.alcoholservice.entity.Alcohol;
import com.haruhanjan.alcoholservice.repository.AlcoholRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AlcoholService {

    private final AlcoholRepository alcoholRepository; // field injection

    public AlcoholResponseDTO save(AlcoholRequestDTO dto) {
        Alcohol save = dto.toEntity();
        Alcohol saved = alcoholRepository.save(save);
        return new AlcoholResponseDTO(saved);
    }

    @Transactional
    public void modify(Long id, AlcoholRequestDTO dto) {
        Alcohol target = alcoholRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        target.modify(dto);
    }


    @Transactional
    public void deleteById(Long id) {
        Alcohol target = alcoholRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        target.delete();
    }

    public List<AlcoholResponseDTO> getAll() {
        List<Alcohol> targets = alcoholRepository.findAll();
        return targets.stream()
            .filter(t -> !t.getBaseTimeEntity().isDeleted())
            .map(AlcoholResponseDTO::new)
            .collect(Collectors.toList());
    }

    public AlcoholResponseDTO get(Long id) {
        Alcohol target = alcoholRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return new AlcoholResponseDTO(target);
    }
}
