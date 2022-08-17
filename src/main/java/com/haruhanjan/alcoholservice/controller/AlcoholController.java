package com.haruhanjan.alcoholservice.controller;

import com.haruhanjan.alcoholservice.dto.CreateRequestDTO;
import com.haruhanjan.alcoholservice.dto.ResponseDTO;
import com.haruhanjan.alcoholservice.dto.ModifyDTO;
import com.haruhanjan.alcoholservice.service.AlcoholService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("alcohol")
public class AlcoholController {
    private final AlcoholService alcoholService;

    @GetMapping
    public ResponseEntity<List<ResponseDTO>> readAll() {
        List<ResponseDTO> result = alcoholService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> create(@RequestBody CreateRequestDTO dto) {
        ResponseDTO result = alcoholService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> patch(@PathVariable Long id,
                                      @RequestBody ModifyDTO dto) {
        alcoholService.modify(id, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        alcoholService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}