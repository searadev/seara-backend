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

import com.searadejesus.searabackend.dto.ModuleDTO;
import com.searadejesus.searabackend.entities.Module;
import com.searadejesus.searabackend.repositories.ModuleRepository;
import com.searadejesus.searabackend.services.exceptions.DataBaseException;
import com.searadejesus.searabackend.services.exceptions.ResourceNotFoundException;

@Service
public class ModuleService {

    @Autowired
    private ModuleRepository repository;

    @Transactional(readOnly = true)
    public Page<ModuleDTO> findAllPaged(Pageable pageable) {
        Page<Module> list = repository.findAll(pageable);
        return list.map(x -> new ModuleDTO(x));
    }

    @Transactional(readOnly = true)
    public ModuleDTO findById(Long id) {
        Optional<Module> obj = repository.findById(id);
        Module entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new ModuleDTO(entity);
    }

    @Transactional
    public ModuleDTO insert(ModuleDTO dto) {

        Module entity = new Module();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new ModuleDTO(entity);
    }    

    @Transactional
    public ModuleDTO update(Long id, ModuleDTO dto) {
        try {
            Module entity = repository.getOne(id);
                copyDtoToEntity(dto, entity);
                entity = repository.save(entity);
                return new ModuleDTO(entity);
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

    private void copyDtoToEntity(ModuleDTO dto, Module entity) {      
        
        entity.setName(dto.getName());
        
    }
    
}
