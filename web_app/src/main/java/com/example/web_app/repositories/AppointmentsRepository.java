package com.example.web_app.repositories;

import com.example.web_app.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentsRepository extends JpaRepository<Appointment, Integer> {
    List<Appointment> findAppointmentsByPatientId(int id);
    List<Appointment> findAppointmentsByDoctorId(int id);
    List<Appointment> findAppointmentsByDiseaseId(int id);
}
