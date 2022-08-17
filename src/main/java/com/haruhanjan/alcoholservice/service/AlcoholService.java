package com.haruhanjan.alcoholservice.service;

import com.haruhanjan.alcoholservice.dto.AlcoholRequest;
import com.haruhanjan.alcoholservice.dto.AlcoholResponse;
import com.haruhanjan.alcoholservice.dto.ModifyDto;
import com.haruhanjan.alcoholservice.entity.Alcohol;
import com.haruhanjan.alcoholservice.entity.AlcoholType;
import com.haruhanjan.alcoholservice.repository.AlcoholRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AlcoholService {

    private final AlcoholRepository alcoholRepository; // field injection

    public AlcoholResponse save(AlcoholRequest dto) {

        Alcohol saved = alcoholRepository.save(dto.toEntity());
        return AlcoholResponse.of(saved);
    }

    @Transactional
    public void modify(Long id, ModifyDto dto) {
        Alcohol target = alcoholRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        Optional.ofNullable(dto.getPrice()).ifPresent(target::setPrice);
        Optional.ofNullable(dto.getAlcoholType()).ifPresent(param -> target.setAlcoholType(AlcoholType.valueOf(param)));
        Optional.ofNullable(dto.getName()).ifPresent(param -> target.setName(param));
        Optional.ofNullable(dto.getProductDate()).ifPresent(param -> target.setProductDate(param));
        Optional.ofNullable(dto.getOriginVolume()).ifPresent(param -> target.setVolume((int) (param*10)));
        Optional.ofNullable(dto.getSeller()).ifPresent(param -> target.setSeller(param));
        Optional.ofNullable(dto.getMadeFrom()).ifPresent(param -> target.setMadeFrom(param));

        target.setUpdatedAt();
    }


    @Transactional
    public void deleteById(Long id) {
        Alcohol target =alcoholRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        target.delete();
    }

    public List<AlcoholResponse> getAll() {
        List<Alcohol> targets = alcoholRepository.findAll();
        return AlcoholResponse.listOf(targets);
    }
}
