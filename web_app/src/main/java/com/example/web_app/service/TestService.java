package com.example.web_app.service;

import com.example.web_app.entity.Test;
import com.example.web_app.repositories.TestsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TestService {
    private final TestsRepository testsRepository;

    @Autowired
    public TestService(TestsRepository testsRepository) {
        this.testsRepository = testsRepository;
    }

    @Transactional
    public void save(Test test){
        testsRepository.save(test);
    }

    @Transactional
    public void delete(int id){
        testsRepository.deleteById(id);
    }

    @Transactional
    public void update(Test test, int id){
        test.setId(id);
        testsRepository.save(test);
    }

    public List<Test> findAll(){
        return testsRepository.findAll();
    }

    public List<Test> findAllTestsByPatientId(int id){
        return testsRepository.findTestsByPatientId(id);
    }
}
