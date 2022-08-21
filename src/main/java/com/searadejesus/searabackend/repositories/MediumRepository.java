package com.searadejesus.searabackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.searadejesus.searabackend.entities.Medium;

@Repository
public interface MediumRepository extends JpaRepository<Medium, Long> {
    
}
