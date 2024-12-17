package com.example.web_app.service;


import com.example.web_app.dto.OfferItemListDTO;
import com.example.web_app.dto.SalesItemListDTO;
import com.example.web_app.entity.Offer;
import com.example.web_app.repositories.OffersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SaleService {

    private final OffersRepository repository;

    private SalesItemListDTO mapToSalesItemListDTO(Offer offer){
        return SalesItemListDTO.builder()
                .offerId(offer.getId())
                .name(offer.getName())
                .build();
    }


    public List<SalesItemListDTO> getAll() {
        return repository.findAll().stream()
                .map(this::mapToSalesItemListDTO)
                .collect(Collectors.toList());
    }
}
