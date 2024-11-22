package com.example.web_app.repositories;

import com.example.web_app.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DoctorsRepository extends JpaRepository<Doctor, Integer> {

    Optional<Doctor> findByName(String name);

}
