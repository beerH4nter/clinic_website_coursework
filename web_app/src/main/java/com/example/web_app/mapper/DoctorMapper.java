package com.example.web_app.mapper;

import com.example.web_app.dto.DoctorDTO;
import com.example.web_app.entity.Doctor;
import com.example.web_app.entity.Shift;
import com.example.web_app.repositories.ShiftsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class DoctorMapper {

    private final ShiftsRepository shiftsRepository;

    @Autowired
    public DoctorMapper(ShiftsRepository shiftsRepository) {
        this.shiftsRepository = shiftsRepository;
    }


    public Doctor toRequest(DoctorDTO doctorDTO) {

        List<Shift> shifts = shiftsRepository.findAllById(doctorDTO.getShiftsId());

        Doctor doctor = new Doctor();

        doctor.setName(doctorDTO.getName());
        doctor.setSurname(doctorDTO.getSurname());
        doctor.setPatronymic(doctorDTO.getPatronymic());
        doctor.setPosition(doctorDTO.getPosition());
        doctor.setDateOfBirth(doctorDTO.getDateOfBirth());
        doctor.setEmail(doctorDTO.getEmail());
        doctor.setPassword(doctorDTO.getPassword());
        doctor.setShifts(shifts);

        return doctor;

    }

    public DoctorDTO toResponse(Doctor doctor){

        DoctorDTO doctorDTO = new DoctorDTO();
        doctorDTO.setName(doctor.getName());
        doctorDTO.setSurname(doctor.getSurname());
        doctorDTO.setPatronymic(doctor.getPatronymic());
        doctorDTO.setPosition(doctor.getPosition());
        doctorDTO.setDateOfBirth(doctor.getDateOfBirth());
        doctorDTO.setEmail(doctor.getEmail());
        doctorDTO.setPassword(doctor.getPassword());
        doctorDTO.setShiftsId(doctor.getShifts().stream().map(Shift::getId).collect(Collectors.toList()));

        return doctorDTO;

    }

}
