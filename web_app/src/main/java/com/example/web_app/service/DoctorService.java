package com.example.web_app.service;

import com.example.web_app.entity.Doctor;
import com.example.web_app.entity.Patient;
import com.example.web_app.repositories.DoctorsRepository;
import com.example.web_app.repositories.PatientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.beans.Transient;
import java.util.List;


@Service
public class DoctorService {

    private final DoctorsRepository doctorsRepository;

    @Autowired
    public DoctorService(DoctorsRepository doctorsRepository) {
        this.doctorsRepository = doctorsRepository;
    }


    @Transactional
    public void save(Doctor doctor){
        doctorsRepository.save(doctor);
    }

    @Transactional
    public void delete(int id){
        doctorsRepository.deleteById(id);
    }

    @Transactional
    public void update(int id, Doctor doctor){
        doctor.setId(id);
        doctorsRepository.save(doctor);
    }


    public List<Doctor> findAll(){
        return doctorsRepository.findAll();

    }

    public Doctor findOneByName(String name){
        return doctorsRepository.findByName(name).orElse(null);
    }

    public Doctor findOneById(int id){
        return doctorsRepository.findById(id).orElse(null);
    }


}
