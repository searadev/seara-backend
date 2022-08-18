package com.searadejesus.searabackend.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.searadejesus.searabackend.dto.MediumDTO;
import com.searadejesus.searabackend.entities.Medium;
import com.searadejesus.searabackend.repositories.MediumRepository;

import com.searadejesus.searabackend.services.exceptions.DataBaseException;
import com.searadejesus.searabackend.services.exceptions.ResourceNotFoundException;

@Service
public class MediumService {

    @Autowired
    private MediumRepository repository;

    @Transactional(readOnly = true)
    public Page<MediumDTO> findAllPaged(Pageable pageable) {
        Page<Medium> list = repository.findAll(pageable);
        return list.map(x -> new MediumDTO(x));
    }

    @Transactional(readOnly = true)
    public MediumDTO findById(Long id) {
        Optional<Medium> obj = repository.findById(id);
        Medium entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new MediumDTO(entity);
    }    

    @Transactional
    public MediumDTO insert(MediumDTO dto) {
        Medium entity = new Medium();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new MediumDTO(entity);
    }    

    @Transactional
    public MediumDTO update(Long id, MediumDTO dto) {
        try {
            Medium entity = repository.getOne(id);
                copyDtoToEntity(dto, entity);
                entity = repository.save(entity);
                return new MediumDTO(entity);
        } 
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found" + id);
        }
    }

    public void delete(Long id) {
        try {
                repository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Id not found" + id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DataBaseException("Integrity violation");
        }
    }

    private void copyDtoToEntity(MediumDTO dto, Medium entity) {

        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
    }
}
