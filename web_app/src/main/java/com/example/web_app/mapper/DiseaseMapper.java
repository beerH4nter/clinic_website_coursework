package com.example.web_app.mapper;


import com.example.web_app.dto.DiseaseDTO;
import com.example.web_app.entity.Appointment;
import com.example.web_app.entity.Disease;
import com.example.web_app.repositories.AppointmentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class DiseaseMapper {

    private final AppointmentsRepository appointmentsRepository;

    @Autowired
    public DiseaseMapper(AppointmentsRepository appointmentsRepository) {
        this.appointmentsRepository = appointmentsRepository;
    }


    public Disease toRequest(DiseaseDTO diseaseDTO) {

        Disease disease = new Disease();

        disease.setName(diseaseDTO.getName());
        disease.setDateStart(diseaseDTO.getDateStart());
        disease.setDateEnd(diseaseDTO.getDateEnd());
        disease.setTreatment(diseaseDTO.getTreatment());


        return disease;

    }

    public DiseaseDTO toResponse(Disease disease){

        DiseaseDTO diseaseDTO = new DiseaseDTO();
        diseaseDTO.setName(disease.getName());
        diseaseDTO.setDateStart(disease.getDateStart());
        diseaseDTO.setDateEnd(disease.getDateEnd());
        diseaseDTO.setTreatment(disease.getTreatment());
        diseaseDTO.setAppointmentsId(disease.getAppointments().stream().map(Appointment::getId).collect(Collectors.toList()));

        return diseaseDTO;

    }
}
