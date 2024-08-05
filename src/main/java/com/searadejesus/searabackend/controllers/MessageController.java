package com.searadejesus.searabackend.controllers;
import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.searadejesus.searabackend.dto.MessageDTO;
import com.searadejesus.searabackend.dto.MessageInsertDTO;
import com.searadejesus.searabackend.dto.MessagePagedDTO;
import com.searadejesus.searabackend.dto.MessageUpdatedDTO;
import com.searadejesus.searabackend.services.MessageService;

@RestController
@RequestMapping(value = "/messages")
public class MessageController {
    
    @Autowired
    private MessageService service;

    @GetMapping
    public ResponseEntity<Page<MessagePagedDTO>> findAll(
        Pageable pageable,
        @RequestParam(value = "fullName", defaultValue = "") String fullName,
        @RequestParam(value = "text", defaultValue = "") String text
        ) {
        Page<MessagePagedDTO> list = service.findAllPaged(pageable, fullName, text);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<Page<MessageDTO>> findAllStatus(
        Pageable pageable,
        @RequestParam(value = "fullName", defaultValue = "") String fullName,
        @RequestParam(value = "text", defaultValue = "") String text
        ) {
        Page<MessageDTO> list = service.findAllStatus(pageable, fullName, text);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<MessageDTO> findById(@PathVariable Long id) {
        MessageDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping(value = "/admin/{id}")
    public ResponseEntity<MessageUpdatedDTO> findByIdAdmin(@PathVariable Long id) {
        MessageUpdatedDTO dto = service.findByIdAdmin(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<MessageInsertDTO> insert(@Valid @RequestBody MessageInsertDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);

    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<MessageUpdatedDTO> update(@Valid @PathVariable Long id, @RequestBody MessageUpdatedDTO dto){
        dto = service.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
