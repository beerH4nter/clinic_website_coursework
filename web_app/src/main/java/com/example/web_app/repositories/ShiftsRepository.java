package com.example.web_app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShiftsRepository extends JpaRepository<Shift, Integer> {
    List<Shift> findShiftsByDoctorId(int id);
}
