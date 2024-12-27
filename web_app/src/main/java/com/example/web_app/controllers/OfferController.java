package com.example.web_app.controllers;


import com.example.web_app.dto.OfferDTO;
import com.example.web_app.dto.OfferItemListDTO;
import com.example.web_app.service.OfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/service")
@RequiredArgsConstructor
public class OfferController {

    private final OfferService service;


    @GetMapping
    public ResponseEntity<List<OfferItemListDTO>> getAll() {
        return ResponseEntity.ok(
                service.getAll()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<OfferDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(
                service.findById(id)
        );
    }

}
