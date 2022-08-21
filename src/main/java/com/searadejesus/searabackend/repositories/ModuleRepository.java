package com.searadejesus.searabackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.searadejesus.searabackend.entities.Module;

@Repository
public interface ModuleRepository extends JpaRepository <Module, Long> {
    
}
