package com.searadejesus.searabackend.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import com.searadejesus.searabackend.dto.ClassUpdatedDTO;
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
    public Page<ClassDTO> findAllPaged(Pageable pageable, String title, Long moduleId) {
        Module module = (moduleId == 0) ? null : moduleRepository.getOne(moduleId);
        Page<Class> list = repository.findPageable(title, module, pageable);
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

        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        String text = entity.getDate().format(formatters);
        LocalDate parsedDate = LocalDate.parse(text, formatters);
        entity.setDate(parsedDate);

        entity.setUser(user);
        entity = repository.save(entity);
        return new ClassDTO(entity);
    }    

    @Transactional
    public ClassDTO update(Long id, ClassUpdatedDTO dto) {
        try {
            Class entity = repository.getOne(id);
                copyDtoToEntity(dto, entity);

                DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd/MM/uuuu");
                String text = entity.getDate().format(formatters);
                LocalDate parsedDate = LocalDate.parse(text, formatters);
                entity.setDate(parsedDate);

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
        entity.setUrl(dto.getUrl());  
        
        Module module = moduleRepository.getOne(dto.getModule().getId());
        entity.setModule(module);

        Medium medium = mediumRepository.getOne(dto.getMedium().getId());
        entity.setMedium(medium);   
        
    }
    
}
