package com.example.web_app.service;

import com.example.web_app.entity.Prescription;
import com.example.web_app.repositories.PrescriptionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PrescriptionService {
    private final PrescriptionsRepository  prescriptionsRepository;

    @Autowired
    public PrescriptionService(PrescriptionsRepository prescriptionsRepository) {
        this.prescriptionsRepository = prescriptionsRepository;
    }

    @Transactional
    public void save(Prescription prescription){
        prescriptionsRepository.save(prescription);
    }

    @Transactional
    public void delete(int id){
        prescriptionsRepository.deleteById(id);

    }

    @Transactional
    public void update(int id, Prescription prescription){
        prescription.setId(id);
        prescriptionsRepository.save(prescription);
    }

    public List<Prescription> findAll(){
        return prescriptionsRepository.findAll();
    }

    public List<Prescription> findAllByPatientId(int id){
        return prescriptionsRepository.findPrescriptionsByPatientId(id);
    }

    public List<Prescription> findAllByDoctorId(int id){
        return prescriptionsRepository.findPrescriptionsByDoctorId(id);
    }

}
