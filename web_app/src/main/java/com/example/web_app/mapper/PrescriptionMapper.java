package com.example.web_app.mapper;

import com.example.web_app.dto.PrescriptionDTO;
import com.example.web_app.dto.TestDTO;
import com.example.web_app.entity.Doctor;
import com.example.web_app.entity.Patient;
import com.example.web_app.entity.Prescription;
import com.example.web_app.entity.Test;
import com.example.web_app.repositories.DoctorsRepository;
import com.example.web_app.repositories.PatientsRepository;
import com.example.web_app.repositories.PrescriptionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PrescriptionMapper {
    private final PrescriptionsRepository prescriptionsRepository;
    private final PatientsRepository patientsRepository;
    private final DoctorsRepository doctorsRepository;

    @Autowired
    public PrescriptionMapper(PrescriptionsRepository prescriptionsRepository, PatientsRepository patientsRepository, DoctorsRepository doctorsRepository) {
        this.prescriptionsRepository = prescriptionsRepository;
        this.patientsRepository = patientsRepository;
        this.doctorsRepository = doctorsRepository;
    }

    public Prescription toRequest(PrescriptionDTO prescriptionDTO) {

        Patient patient = patientsRepository.findById(prescriptionDTO.getPatientId()).orElseThrow(() ->
                new RuntimeException("Patient with ID " + prescriptionDTO.getPatientId() + " not found"));
        Doctor doctor = doctorsRepository.findById(prescriptionDTO.getPatientId()).orElseThrow(() ->
                new RuntimeException("Patient with ID " + prescriptionDTO.getPatientId() + " not found"));

        Prescription prescription = new Prescription();
        prescription.setName(prescriptionDTO.getName());
        prescription.setIssuedAt(prescriptionDTO.getIssuedAt());
        prescription.setExpiredAt(prescriptionDTO.getExpiredAt());
        prescription.setPatient(patient);
        prescription.setDoctor(doctor);
        return prescription;

    }

    public PrescriptionDTO toResponse(Prescription prescription){
        PrescriptionDTO prescriptionDTO = new PrescriptionDTO();
        prescriptionDTO.setName(prescription.getName());
        prescriptionDTO.setIssuedAt(prescription.getIssuedAt());
        prescriptionDTO.setExpiredAt(prescription.getExpiredAt());
        prescriptionDTO.setDoctorSurname(prescription.getDoctor().getSurname());
        prescriptionDTO.setPatientSurname(prescription.getPatient().getSurname());
        return prescriptionDTO;

    }

}
