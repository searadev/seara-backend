package com.searadejesus.searabackend.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.searadejesus.searabackend.entities.Module;

@Repository
public interface ModuleRepository extends JpaRepository <Module, Long> {

    @Query("SELECT DISTINCT obj FROM Module obj ORDER BY obj.id DESC")  
    Page<Module> findAll(Pageable pageable);
    
}
