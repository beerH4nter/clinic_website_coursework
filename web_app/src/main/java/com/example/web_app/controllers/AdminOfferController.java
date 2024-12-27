package com.example.web_app.controllers;

import com.example.web_app.dto.OfferDTO;
import com.example.web_app.service.OfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/offer")
@RequiredArgsConstructor
public class AdminOfferController {
    private final OfferService service;
//
//    @PostMapping
//    public OfferDTO createOffer(@RequestBody OfferDTO dto) {
//        // Пример. Реально нужно проверить DTO, сохранить в БД, вернуть созданное
//        return service.create(dto);
//    }
//
//    @PutMapping("/{id}")
//    public OfferDTO updateOffer(@PathVariable Long id, @RequestBody OfferDTO dto) {
//        return service.update(id, dto);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteOffer(@PathVariable Long id) {
//        service.delete(id);
//    }
//
//    @GetMapping
//    public List<OfferDTO> getAllOffers() {
//        return service.getAllOffers();
//    }
//
//    @GetMapping("/{id}")
//    public OfferDTO getOneOffer(@PathVariable Long id) {
//        return service.findById(id);
//    }
}

