package com.searadejesus.searabackend.resources;

import org.springframework.beans.factory.annotation.Autowired;

import com.searadejesus.searabackend.dto.AuthorDTO;
import com.searadejesus.searabackend.services.AuthorService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
@RequestMapping(value = "/authors")
public class AuthorResource {

    @Autowired
    private AuthorService service;

    @GetMapping
    public ResponseEntity<Page<AuthorDTO>> findAll(Pageable pageable) {
        Page<AuthorDTO> list = service.findAllPaged(pageable);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AuthorDTO> findById(@PathVariable Long id) {
        AuthorDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);

    } 
    
}
