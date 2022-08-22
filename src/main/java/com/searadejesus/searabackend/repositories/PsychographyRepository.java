package com.searadejesus.searabackend.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.searadejesus.searabackend.entities.Psychography;

@Repository
public interface PsychographyRepository extends JpaRepository <Psychography, Long> {

    @Query("SELECT DISTINCT obj FROM Psychography obj WHERE "
            + "(LOWER(obj.fullName) LIKE LOWER(CONCAT('%',:fullName,'%')) ) AND "
            + "(LOWER(obj.text) LIKE LOWER(CONCAT('%',:text,'%')) ) AND "
            + "(obj.status = true)") 
    Page<Psychography> find(Pageable pageable, String fullName, String text);
    
}
