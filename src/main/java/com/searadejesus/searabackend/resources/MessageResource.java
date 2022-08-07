package com.searadejesus.searabackend.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.searadejesus.searabackend.dto.MessageDTO;
import com.searadejesus.searabackend.services.MessageService;

@RestController
@RequestMapping(value = "/messages")
public class MessageResource {
    
    @Autowired
    private MessageService service;

    @GetMapping
    public ResponseEntity<Page<MessageDTO>> findAll(Pageable pageable) {
        Page<MessageDTO> list = service.findAllPaged(pageable);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<MessageDTO> findById(@PathVariable Long id) {
        MessageDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);

    }
}