package com.haruhanjan.alcoholservice.controller;

import com.haruhanjan.alcoholservice.dto.AlcoholDto;
import com.haruhanjan.alcoholservice.service.AlcoholService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AlcoholController {
    @Autowired
    private AlcoholService alcoholService;

    @GetMapping("/")
    public String hello() {
        return "hello";
    }

//    @GetMapping("/alcohol")
//    public ResponseEntity readAllAlcohol(){
//        List<AlcoholDto> result =alcoholService.getAllAlcohol();
//        return ResponseEntity.status(HttpStatus.OK).body(result);
//    }

    @PostMapping("/alcohol")
    public ResponseEntity createAlcohol(@RequestBody AlcoholDto dto){
        alcoholService.saveAlcohol(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @PatchMapping("/alcohol")
    public ResponseEntity patchAlcohol(@RequestBody AlcoholDto dto){
        alcoholService.modify(dto);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @GetMapping("/alcohol/{id}")
    public ResponseEntity deleteAlcohol(@PathVariable Long id){
        alcoholService.deleteAlcoholById(id);
        return ResponseEntity.status(HttpStatus.OK).body(id);
    }
}