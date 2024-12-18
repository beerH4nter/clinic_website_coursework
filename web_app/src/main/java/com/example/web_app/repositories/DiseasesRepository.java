package com.example.web_app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DiseasesRepository extends JpaRepository<Disease, Integer> {
    Optional<Disease> findByName(String name);
}
