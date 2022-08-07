package com.searadejesus.searabackend.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.searadejesus.searabackend.dto.PsychographyDTO;
import com.searadejesus.searabackend.entities.Psychography;
import com.searadejesus.searabackend.repositories.PsychographyRepository;

import com.searadejesus.searabackend.services.exceptions.ResourceNotFoundException;

@Service
public class PsychographyService {

    @Autowired
    private PsychographyRepository repository;

    @Transactional(readOnly = true)
    public Page<PsychographyDTO> findAllPaged(Pageable pageable) {
        Page<Psychography> list = repository.findAll(pageable);
        return list.map(x -> new PsychographyDTO(x));
    }

    @Transactional(readOnly = true)
    public PsychographyDTO findById(Long id) {
        Optional<Psychography> obj = repository.findById(id);
        Psychography entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new PsychographyDTO(entity);
    }
    
}
