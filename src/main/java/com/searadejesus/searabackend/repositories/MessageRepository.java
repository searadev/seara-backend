package com.searadejesus.searabackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.searadejesus.searabackend.entities.Message;

@Repository
public interface MessageRepository extends JpaRepository <Message, Long> {

    @Query("SELECT DISTINCT obj FROM Message obj WHERE "
            + "(LOWER(obj.fullName) LIKE LOWER(CONCAT('%',:fullName,'%')) ) AND "
            + "(LOWER(obj.title) LIKE LOWER(CONCAT('%',:title,'%')) ) AND "
            + "(obj.status = true)"
            + "GROUP BY obj.id, obj.date " 
            + "ORDER BY obj.date DESC"  ) 
    Page<Message> find(Pageable pageable, String fullName, String title);

    @Query("SELECT DISTINCT obj FROM Message obj WHERE "
            + "(LOWER(obj.fullName) LIKE LOWER(CONCAT('%',:fullName,'%')) ) AND "
            + "(LOWER(obj.title) LIKE LOWER(CONCAT('%',:title,'%')) )  "
            + "GROUP BY obj.id, obj.date " 
            + "ORDER BY obj.date DESC"  )
    Page<Message> findAllStatus(Pageable pageable, String fullName, String title);
    
}
