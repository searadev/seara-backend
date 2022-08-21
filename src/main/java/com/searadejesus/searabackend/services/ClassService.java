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

import com.searadejesus.searabackend.dto.ClassDTO;
import com.searadejesus.searabackend.dto.ClassInsertDTO;
import com.searadejesus.searabackend.entities.Class;
import com.searadejesus.searabackend.entities.User;
import com.searadejesus.searabackend.repositories.ClassRepository;
import com.searadejesus.searabackend.repositories.MediumRepository;
import com.searadejesus.searabackend.repositories.ModuleRepository;
import com.searadejesus.searabackend.repositories.UserRepository;
import com.searadejesus.searabackend.services.exceptions.DataBaseException;
import com.searadejesus.searabackend.services.exceptions.ResourceNotFoundException;
import com.searadejesus.searabackend.entities.Medium;
import com.searadejesus.searabackend.entities.Module;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Service
public class ClassService {

    @Autowired
    private ClassRepository repository;

    @Autowired
    private ModuleRepository moduleRepository;

    @Autowired
    private MediumRepository mediumRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public Page<ClassDTO> findAllPaged(Pageable pageable) {
        Page<Class> list = repository.findAll(pageable);
        return list.map(x -> new ClassDTO(x));
    }

    @Transactional(readOnly = true)
    public ClassDTO findById(Long id) {
        Optional<Class> obj = repository.findById(id);
        Class entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new ClassDTO(entity);
    }

    @Transactional
    public ClassDTO insert(ClassInsertDTO dto) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName(); 
        User user = userRepository.findByEmail(login);

        Class entity = new Class();
        copyDtoToEntity(dto, entity);
        entity.setUser(user);
        entity = repository.save(entity);
        return new ClassDTO(entity);
    }    

    @Transactional
    public ClassDTO update(Long id, ClassDTO dto) {
        try {
            Class entity = repository.getOne(id);
                copyDtoToEntity(dto, entity);
                entity = repository.save(entity);
                return new ClassDTO(entity);
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

    private void copyDtoToEntity(ClassDTO dto, Class entity) {
        
        entity.setDate(dto.getDate());
        entity.setTitle(dto.getTitle());
        entity.setUri(dto.getUri());  
        
        Module module = moduleRepository.getOne(dto.getMedium().getId());
        entity.setModule(module);

        Medium medium = mediumRepository.getOne(dto.getMedium().getId());
        entity.setMedium(medium);   
        
    }
    
}
