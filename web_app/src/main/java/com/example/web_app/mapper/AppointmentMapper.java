package com.example.web_app.mapper;

import com.example.web_app.dto.AppointmentDTO;
import com.example.web_app.dto.ShiftDTO;
import com.example.web_app.entity.*;
import com.example.web_app.repositories.DiseasesRepository;
import com.example.web_app.repositories.DoctorsRepository;
import com.example.web_app.repositories.PatientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AppointmentMapper {

    private final DiseasesRepository diseasesRepository;
    private final DoctorsRepository doctorsRepository;
    private final PatientsRepository patientsRepository;

    @Autowired
    public AppointmentMapper(DiseasesRepository diseasesRepository, DoctorsRepository doctorsRepository, PatientsRepository patientsRepository) {
        this.diseasesRepository = diseasesRepository;
        this.doctorsRepository = doctorsRepository;
        this.patientsRepository = patientsRepository;
    }

    public Appointment toRequest(AppointmentDTO appointmentDTO) {

        Disease disease = diseasesRepository.findById(appointmentDTO.getDiseaseId()).orElseThrow(() ->
                new RuntimeException("Disease with ID " + appointmentDTO.getDiseaseId() + " not found"));

        Doctor doctor = doctorsRepository.findById(appointmentDTO.getDoctorId()).orElseThrow(() ->
                new RuntimeException("Doctor with ID " + appointmentDTO.getDoctorId() + " not found"));

        Patient patient = patientsRepository.findById(appointmentDTO.getPatientId()).orElseThrow(() ->
                new RuntimeException("Patient with ID " + appointmentDTO.getPatientId() + " not found"));

        Appointment appointment = new Appointment();

        appointment.setDate(appointmentDTO.getDate());
        appointment.setTime(appointmentDTO.getTime());
        appointment.setStatus(appointmentDTO.getStatus());
        appointment.setReason(appointmentDTO.getReason());
        appointment.setDiagnose(appointmentDTO.getDiagnose());
        appointment.setDrugs(appointmentDTO.getDrugs());
        appointment.setDoctorNotes(appointmentDTO.getDoctorNotes());
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setDisease(disease);

        return appointment;

    }

    public AppointmentDTO toResponse(Appointment appointment){

        AppointmentDTO appointmentDTO = new AppointmentDTO();
        appointmentDTO.setDate(appointment.getDate());
        appointmentDTO.setTime(appointment.getTime());
        appointmentDTO.setStatus(appointment.getStatus());
        appointmentDTO.setReason(appointment.getReason());
        appointmentDTO.setDiagnose(appointment.getDiagnose());
        appointmentDTO.setDrugs(appointment.getDrugs());
        appointmentDTO.setDoctorNotes(appointment.getDoctorNotes());
        appointmentDTO.setPatientSurname(appointment.getPatient().getSurname());
        appointmentDTO.setDoctorSurname(appointment.getDoctor().getSurname());
        appointmentDTO.setDiseaseName(appointment.getDisease().getName());
        return appointmentDTO;

    }

}
