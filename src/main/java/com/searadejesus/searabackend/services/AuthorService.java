package com.searadejesus.searabackend.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.searadejesus.searabackend.dto.AuthorDTO;
import com.searadejesus.searabackend.entities.Author;
import com.searadejesus.searabackend.repositories.AuthorRepository;

import com.searadejesus.searabackend.services.exceptions.ResourceNotFoundException;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository repository;

    @Transactional(readOnly = true)
    public Page<AuthorDTO> findAllPaged(Pageable pageable) {
        Page<Author> list = repository.findAll(pageable);
        return list.map(x -> new AuthorDTO(x));
    }

    @Transactional(readOnly = true)
    public AuthorDTO findById(Long id) {
        Optional<Author> obj = repository.findById(id);
        Author entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new AuthorDTO(entity);
    }
    
}
