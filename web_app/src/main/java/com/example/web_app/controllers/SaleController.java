package com.example.web_app.controllers;

import com.example.web_app.dto.SalesItemListDTO;
import com.example.web_app.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sale")
@RequiredArgsConstructor
public class SaleController {

    private final SaleService service;

    @GetMapping
    public ResponseEntity<List<SalesItemListDTO>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }

}
