package com.example.web_app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientsRepository extends JpaRepository<Patient, Integer> {

    Optional<Patient> findByName(String name);

    Optional<Patient> findByEmail(String email);

}
