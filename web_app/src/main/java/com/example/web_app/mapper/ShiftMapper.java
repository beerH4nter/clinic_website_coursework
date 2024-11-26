package com.example.web_app.mapper;

import com.example.web_app.dto.ShiftDTO;
import com.example.web_app.entity.Doctor;
import com.example.web_app.entity.Shift;
import com.example.web_app.repositories.DoctorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShiftMapper {

    private final DoctorsRepository doctorsRepository;

    @Autowired
    public ShiftMapper(DoctorsRepository doctorsRepository) {
        this.doctorsRepository = doctorsRepository;
    }

    public Shift toRequest(ShiftDTO shiftDTO) {

        Doctor doctor = doctorsRepository.findById(shiftDTO.getDoctorId()).orElseThrow(() ->
                new RuntimeException("Doctor with ID " + shiftDTO.getDoctorId() + " not found"));
        Shift shift = new Shift();
        shift.setDate(shiftDTO.getDate());
        shift.setTimeStart(shiftDTO.getTimeStart());
        shift.setTimeEnd(shiftDTO.getTimeEnd());
        shift.setLunchTime(shiftDTO.getLunchTime());
        shift.setDoctor(doctor);
        return shift;

    }

    public ShiftDTO toResponse(Shift shift){
        ShiftDTO response = new ShiftDTO();
        response.setDate(shift.getDate());
        response.setTimeStart(shift.getTimeStart());
        response.setTimeEnd(shift.getTimeEnd());
        response.setLunchTime(shift.getLunchTime());
        response.setDoctorSurname(shift.getDoctor().getSurname());
        return response;

    }

}
