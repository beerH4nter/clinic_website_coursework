package com.example.web_app.service;

import com.example.web_app.entity.Appointment;
import com.example.web_app.repositories.AppointmentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AppointmentService {
    private final AppointmentsRepository appointmentsRepository;

    @Autowired
    public AppointmentService(AppointmentsRepository appointmentsRepository) {
        this.appointmentsRepository = appointmentsRepository;
    }

    @Transactional
    public void save(Appointment appointment){
        appointmentsRepository.save(appointment);
    }

    @Transactional
    public void delete(int id){
        appointmentsRepository.deleteById(id);
    }

    @Transactional
    public void update(int id, Appointment appointment){
        appointment.setId(id);
        appointmentsRepository.save(appointment);
    }

    public List<Appointment> findAll(){
        return appointmentsRepository.findAll();
    }

    public Appointment findOneById(int id){
        return appointmentsRepository.findById(id).orElse(null);
    }

    public List<Appointment> findAllByPatientId(int id){
        return appointmentsRepository.findAppointmentsByPatientId(id);
    }

    public List<Appointment> findAllByDoctorId(int id){
        return appointmentsRepository.findAppointmentsByDoctorId(id);
    }

    public List<Appointment> findAllByDiseaseId(int id){
        return appointmentsRepository.findAppointmentsByDiseaseId(id);
    }

    public List<Appointment> findAllByDate(String date){
        return appointmentsRepository.findAppointmentsByDate(date);
    }
}
