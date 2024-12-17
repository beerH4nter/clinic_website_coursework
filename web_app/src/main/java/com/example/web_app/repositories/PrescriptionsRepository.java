package com.example.web_app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrescriptionsRepository extends JpaRepository<Prescription, Integer> {
    List<Prescription> findPrescriptionsByDoctorId(int id);
    List<Prescription> findPrescriptionsByPatientId(int id);
}
