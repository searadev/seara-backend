package com.searadejesus.searabackend.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.searadejesus.searabackend.dto.MessageDTO;
import com.searadejesus.searabackend.entities.Message;
import com.searadejesus.searabackend.repositories.MessageRepository;

import com.searadejesus.searabackend.services.exceptions.ResourceNotFoundException;

@Service
public class MessageService {

    @Autowired
    private MessageRepository repository;

    @Transactional(readOnly = true)
    public Page<MessageDTO> findAllPaged(Pageable pageable) {
        Page<Message> list = repository.findAll(pageable);
        return list.map(x -> new MessageDTO(x));
    }

    @Transactional(readOnly = true)
    public MessageDTO findById(Long id) {
        Optional<Message> obj = repository.findById(id);
        Message entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new MessageDTO(entity);
    }
    
}
