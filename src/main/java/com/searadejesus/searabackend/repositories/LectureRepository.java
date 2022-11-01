package com.searadejesus.searabackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import com.searadejesus.searabackend.entities.Lecture;

@Repository
public interface LectureRepository extends JpaRepository <Lecture, Long> {

    @Query("SELECT DISTINCT obj FROM Lecture obj WHERE " 
            + "(LOWER(obj.title) LIKE LOWER(CONCAT('%',:title,'%')) ) ")
    Page<Lecture> findPageable(String title, Pageable pageable);
    
}
