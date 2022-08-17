package com.searadejesus.searabackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.searadejesus.searabackend.entities.Psychography;

@Repository
public interface PsychographyRepository extends JpaRepository <Psychography, Long> {

    
    
}
