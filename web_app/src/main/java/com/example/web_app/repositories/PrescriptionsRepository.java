package com.example.web_app.repositories;

import com.example.web_app.entity.Doctor;
import com.example.web_app.entity.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrescriptionsRepository extends JpaRepository<Prescription, Integer> {
    List<Prescription> findPrescriptionsByDoctorId(int id);
    List<Prescription> findPrescriptionsByPatientId(int id);
}
