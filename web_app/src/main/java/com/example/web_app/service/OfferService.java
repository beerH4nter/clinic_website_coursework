package com.example.web_app.service;

import com.example.web_app.dto.OfferDTO;
import com.example.web_app.dto.OfferItemListDTO;
import com.example.web_app.entity.Offer;
import com.example.web_app.repositories.OffersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class OfferService {

    private final OffersRepository repository;


    private OfferItemListDTO mapToOfferItemListDTO(Offer offer){
        return OfferItemListDTO.builder()
                .offerId(offer.getId())
                .name(offer.getName())
                .build();
    }

    private OfferDTO mapToOfferDTO(Offer offer){
        return OfferDTO.builder()
                .name(offer.getName())
                .description(offer.getDescription())
                .price(offer.getPrice())
                .isSale(offer.getIsSale())
                .build();
    }

    public List<OfferItemListDTO> getAll() {
        return repository.findAll().stream()
                .map(this::mapToOfferItemListDTO)
                .collect(Collectors.toList());
    }


    public OfferDTO findById(Long id) {
        return mapToOfferDTO(repository.getById(id));
    }
}
