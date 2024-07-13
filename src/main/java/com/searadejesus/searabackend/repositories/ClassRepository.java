package com.searadejesus.searabackend.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.searadejesus.searabackend.entities.Class;
import com.searadejesus.searabackend.entities.Module;

@Repository
public interface ClassRepository extends JpaRepository <Class, Long> {

    @Query("SELECT DISTINCT obj FROM Class obj WHERE " 
            + "(LOWER(obj.title) LIKE LOWER(CONCAT('%',:title,'%')) ) AND"
            + "(:module IS NULL OR :module = obj.module )"
            + "GROUP BY obj.id, obj.date " 
            + "ORDER BY obj.date DESC" )
    Page<Class> findPageable(String title, Module module, Pageable pageable);
    
}
