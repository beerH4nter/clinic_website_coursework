package com.example.web_app.mapper;

import com.example.web_app.dto.ShiftDTO;
import com.example.web_app.entity.Doctor;
import com.example.web_app.entity.Shift;
import com.example.web_app.repositories.DoctorsRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ShiftMapper {

    private static DoctorsRepository doctorsRepository;

    public static Shift toRequest(ShiftDTO shiftDTO) {

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

    public static ShiftDTO toResponse(Shift shift){
        ShiftDTO response = new ShiftDTO();
        response.setDate(shift.getDate());
        response.setTimeStart(shift.getTimeStart());
        response.setTimeEnd(shift.getTimeEnd());
        response.setLunchTime(shift.getLunchTime());
        response.setDoctorSurname(shift.getDoctor().getSurname());
        return response;

    }

}
