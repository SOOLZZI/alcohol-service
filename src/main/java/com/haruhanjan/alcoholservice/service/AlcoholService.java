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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AlcoholService {

    private final AlcoholRepository alcoholRepository; // field injection

    public AlcoholResponseDTO save(CreateAlcoholRequestDTO dto) {
        Alcohol save = dto.toEntity();
        Alcohol saved = alcoholRepository.save(save);
        return new AlcoholResponseDTO(saved);
    }

    @Transactional
    public ModifyAlcoholDTO modify(Long id, ModifyAlcoholDTO dto) {
        Alcohol target = alcoholRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        ModifyAlcoholDTO modified = target.modify(dto);
        return modified;
    }


    @Transactional
    public void deleteById(Long id) {
        Alcohol target = alcoholRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        target.delete();
    }

    public List<AlcoholResponseDTO> getAll() {
        List<Alcohol> targets = alcoholRepository.findAll();

        return targets.stream()
                .map(AlcoholResponseDTO::new)
                .collect(Collectors.toList());
    }
}
