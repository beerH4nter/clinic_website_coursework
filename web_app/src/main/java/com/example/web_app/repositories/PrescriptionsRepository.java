package com.example.web_app.repositories;

import com.example.web_app.entity.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrescriptionsRepository extends JpaRepository<Prescription, Long> {
    List<Prescription> findAllByDoctorId(Long id);
    List<Prescription> findAllByPatientId(Long id);
}
