package com.example.web_app.repositories;

import com.example.web_app.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorsRepository extends JpaRepository<Doctor, Integer> {

    Optional<Doctor> findByName(String name);
    Optional<Doctor> findByNameAndSurnameAndPatronymicAndPosition(
            String name,
            String surname,
            String patronymic,
            String position
    );

}
