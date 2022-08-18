package com.searadejesus.searabackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.searadejesus.searabackend.entities.Lecture;

@Repository
public interface LectureRepository extends JpaRepository <Lecture, Long> {
    
}
