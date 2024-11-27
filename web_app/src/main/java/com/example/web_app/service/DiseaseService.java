package com.example.web_app.service;

import com.example.web_app.entity.Disease;
import com.example.web_app.repositories.DiseasesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class DiseaseService {
    private final DiseasesRepository diseasesRepository;

    @Autowired
    public DiseaseService(DiseasesRepository diseasesRepository) {
        this.diseasesRepository = diseasesRepository;
    }

    @Transactional
    public void save(Disease disease){
        diseasesRepository.save(disease);
    }

    @Transactional
    public void delete(int id){
        diseasesRepository.deleteById(id);
    }

    @Transactional
    public void update(int id, Disease disease){
        disease.setId(id);
        diseasesRepository.save(disease);
    }

    public List<Disease> findAll(){
        return diseasesRepository.findAll();
    }

    public Disease findOneById(int id){
        return diseasesRepository.findById(id).orElse(null);
    }
}
