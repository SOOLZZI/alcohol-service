package com.haruhanjan.alcoholservice.controller;

import com.haruhanjan.alcoholservice.dto.AlcoholDto;
import com.haruhanjan.alcoholservice.dto.AlcoholRequest;
import com.haruhanjan.alcoholservice.dto.AlcoholResponse;
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
    public ResponseEntity<List<AlcoholResponse>> readAllAlcohol() {
        List<AlcoholResponse> result = alcoholService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping("/alcohol")
    public ResponseEntity createAlcohol(@RequestBody AlcoholDto dto){
        alcoholService.saveAlcohol(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @PatchMapping("/alcohol")
    public ResponseEntity patchAlcohol(@RequestBody AlcoholDto dto){
        alcoholService.modify(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/alcohol/{id}")
    public ResponseEntity deleteAlcohol(@PathVariable Long id){
        alcoholService.deleteAlcoholById(id);
        return ResponseEntity.status(HttpStatus.OK).body(id);
    }
}