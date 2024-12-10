package com.example.web_app.service;


import com.example.web_app.entity.Patient;
import com.example.web_app.repositories.PatientsRepository;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    private final PatientsRepository patientsRepository;

    @Autowired
    public PatientService(PatientsRepository patientsRepository) {
        this.patientsRepository = patientsRepository;
    }

    @Transactional
    public void save(Patient patient){
        patientsRepository.save(patient);
    }

    @Transactional
    public void delete(int id){
        patientsRepository.deleteById(id);
    }

    @Transactional
    public void update(int id, Patient patient){
        patient.setId(id);
        patientsRepository.save(patient);
    }

    public List<Patient> findAll(){
        return patientsRepository.findAll();
    }

    public Patient findOneById(int id){
        return patientsRepository.findById(id).orElse(null);
    }

    public  Patient findOneByName(String name){
        return patientsRepository.findByName(name).orElse(null);
    }

    public Patient findOneByEmail(String email){
        return patientsRepository.findByEmail(email).orElse(null);
    }



}
