package com.example.web_app.service;

import com.example.web_app.entity.Shift;
import com.example.web_app.repositories.ShiftsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ShiftService {
    private final ShiftsRepository shiftsRepository;

    @Autowired
    public ShiftService(ShiftsRepository shiftsRepository) {
        this.shiftsRepository = shiftsRepository;
    }

    @Transactional
    public void save(Shift shift){
        shiftsRepository.save(shift);
    }

    @Transactional
    public void delete(int id){
        shiftsRepository.deleteById(id);
    }

    @Transactional
    public void update(Shift shift, int id){
        shift.setId(id);
        shiftsRepository.save(shift);
    }

    public List<Shift> findAll(){
        return shiftsRepository.findAll();
    }

    public List<Shift> findAllShiftsByDoctorId(int id){
        return shiftsRepository.findShiftsByDoctorId(id);
    }
}
