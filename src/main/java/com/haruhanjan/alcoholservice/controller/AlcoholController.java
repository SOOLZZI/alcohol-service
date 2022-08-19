package com.haruhanjan.alcoholservice.controller;

import com.haruhanjan.alcoholservice.dto.AlcoholResponseDTO;
import com.haruhanjan.alcoholservice.dto.CreateAlcoholRequestDTO;
import com.haruhanjan.alcoholservice.dto.ModifyAlcoholDTO;
import com.haruhanjan.alcoholservice.service.AlcoholService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/alcohol")
public class AlcoholController {
    private final AlcoholService alcoholService;

    @GetMapping
    public ResponseEntity<List<AlcoholResponseDTO>> readAll() {
        List<AlcoholResponseDTO> result = alcoholService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping
    public ResponseEntity<AlcoholResponseDTO> create(@RequestBody CreateAlcoholRequestDTO dto) {
        AlcoholResponseDTO result = alcoholService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> patch(@PathVariable Long id,
                                      @RequestBody ModifyAlcoholDTO dto) {
        alcoholService.modify(id, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        alcoholService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}