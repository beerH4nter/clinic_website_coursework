package com.example.web_app.repositories;

import com.example.web_app.entity.Appointment;
import com.example.web_app.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentsRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findAllByPatientId(Long appointmentId);
    List<Appointment> findAllByDoctorId(Long doctorId);

    @Query("SELECT a FROM Appointment a WHERE a.patient = :patient AND a.doctor.id = :doctorId AND a.dateTime BETWEEN :startOfDay AND :endOfDay")
    Optional<Appointment> findTodayAppointmentByPatientAndDoctor(
            @Param("patient") Patient patient,
            @Param("doctorId") Long doctorId,
            @Param("startOfDay") LocalDateTime startOfDay,
            @Param("endOfDay") LocalDateTime endOfDay
    );

}
