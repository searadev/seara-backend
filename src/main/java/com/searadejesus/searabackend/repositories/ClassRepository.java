package com.searadejesus.searabackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.searadejesus.searabackend.entities.Class;

@Repository
public interface ClassRepository extends JpaRepository <Class, Long> {
    
}
