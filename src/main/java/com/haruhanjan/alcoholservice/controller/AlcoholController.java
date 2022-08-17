package com.haruhanjan.alcoholservice.controller;

import com.haruhanjan.alcoholservice.dto.AlcoholRequest;
import com.haruhanjan.alcoholservice.dto.AlcoholResponse;
import com.haruhanjan.alcoholservice.dto.ModifyDto;
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
    public ResponseEntity<List<AlcoholResponse>> readAll() {
        List<AlcoholResponse> result = alcoholService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping
    public ResponseEntity<AlcoholResponse> create(@RequestBody AlcoholRequest dto) {
        AlcoholResponse result = alcoholService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> patchPrice(@PathVariable Long id,
                                           @RequestBody ModifyDto dto) {
        alcoholService.modify(id, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        alcoholService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}