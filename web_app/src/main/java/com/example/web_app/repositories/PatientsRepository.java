package com.example.web_app.repositories;

import com.example.web_app.entity.Doctor;
import com.example.web_app.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientsRepository extends JpaRepository<Patient, Long> {

    Optional<Patient> findByEmail(String email);

    Optional<Patient> findByNameAndSurnameAndPatronymic(
            String firstName,
            String lastName,
            String middleName
    );
}
