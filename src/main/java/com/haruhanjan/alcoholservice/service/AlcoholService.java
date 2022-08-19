package com.haruhanjan.alcoholservice.service;

import com.haruhanjan.alcoholservice.dto.AlcoholResponseDTO;
import com.haruhanjan.alcoholservice.dto.CreateAlcoholRequestDTO;
import com.haruhanjan.alcoholservice.dto.ModifyAlcoholDTO;
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

    public AlcoholResponseDTO save(CreateAlcoholRequestDTO dto) {
        Alcohol save = dto.toEntity();
        save.setCreatedAt();
        Alcohol saved = alcoholRepository.save(save);
        return AlcoholResponseDTO.of(saved);
    }

    @Transactional
    public void modify(Long id, ModifyAlcoholDTO dto) {
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
        return AlcoholResponseDTO.listOf(targets);
    }
}
