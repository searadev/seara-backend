package com.searadejesus.searabackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.searadejesus.searabackend.entities.Message;

@Repository
public interface MessageRepository extends JpaRepository <Message, Long> {
    
}
