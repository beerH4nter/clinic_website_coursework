package com.example.web_app.repositories;

import com.example.web_app.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestsRepository extends JpaRepository<Test, Long> {
    List<Test> findAllByPatientId(Long id);
}
