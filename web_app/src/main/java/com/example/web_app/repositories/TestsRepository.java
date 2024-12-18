package com.example.web_app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestsRepository extends JpaRepository<Test, Integer> {
    List<Test> findTestsByPatientId(int id);
}
