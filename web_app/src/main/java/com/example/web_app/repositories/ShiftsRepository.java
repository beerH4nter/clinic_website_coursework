package com.example.web_app.repositories;

import com.example.web_app.entity.Shift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShiftsRepository extends JpaRepository<Shift, Integer> {
    List<Shift> findShiftsByDoctorId(int id);
}
