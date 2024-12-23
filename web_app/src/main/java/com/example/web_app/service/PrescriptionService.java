package com.example.web_app.service;

import com.example.web_app.dto.PrescriptionItemListDTO;
import com.example.web_app.entity.Prescription;
import com.example.web_app.repositories.PrescriptionsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PrescriptionService {

    private final PrescriptionsRepository repository;

    private PrescriptionItemListDTO mapToPrescriptionItemListDTO(Prescription prescription){
        return PrescriptionItemListDTO.builder()
                .name(prescription.getName())
                .issuedAt(prescription.getIssuedAt())
                .expiredAt(prescription.getExpiredAt())
                .build();
    }
    public List<PrescriptionItemListDTO> getAllByPatientId(Long patientId) {
        return repository.findPrescriptionsByPatientId(patientId).stream()
                .map(this::mapToPrescriptionItemListDTO)
                .collect(Collectors.toList());
    }
}
