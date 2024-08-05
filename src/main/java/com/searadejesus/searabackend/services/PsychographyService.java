package com.searadejesus.searabackend.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.searadejesus.searabackend.dto.PsychographyDTO;
import com.searadejesus.searabackend.dto.PsychographyInsertDTO;
import com.searadejesus.searabackend.dto.PsychographyPagedDTO;
import com.searadejesus.searabackend.dto.PsychographyUpdatedDTO;
import com.searadejesus.searabackend.entities.Medium;
import com.searadejesus.searabackend.entities.Psychography;
import com.searadejesus.searabackend.entities.User;
import com.searadejesus.searabackend.repositories.MediumRepository;
import com.searadejesus.searabackend.repositories.PsychographyRepository;
import com.searadejesus.searabackend.repositories.UserRepository;
import com.searadejesus.searabackend.services.exceptions.DataBaseException;
import com.searadejesus.searabackend.services.exceptions.ResourceNotFoundException;

@Service
public class PsychographyService {    

    public static Logger logger = LoggerFactory.getLogger(PsychographyService.class);

    @Autowired
    private PsychographyRepository repository;

    @Autowired
    private MediumRepository mediumRepository;

    @Autowired
    private UserRepository userRepository;    

    @Transactional(readOnly = true)
    public Page<PsychographyPagedDTO> findAllPaged(Pageable pageable, String fullName, String text) {
        Page<Psychography> list = repository.find(pageable, fullName, text);
        return list.map(x -> new PsychographyPagedDTO(x)); 
    }

    @Transactional(readOnly = true)
    public Page<PsychographyPagedDTO> findAllStatus(Pageable pageable, String fullName, String text) {
        Page<Psychography> list = repository.findAllStatus(pageable, fullName, text);
        return list.map(x -> new PsychographyPagedDTO(x));
    }

    @Transactional(readOnly = true)
    public PsychographyDTO findById(Long id) {
        Optional<Psychography> obj = repository.findById(id);
        Psychography entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new PsychographyDTO(entity);
    }

    @Transactional(readOnly = true)
    public PsychographyUpdatedDTO findByIdAdmin(Long id) {
        Optional<Psychography> obj = repository.findById(id);
        Psychography entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new PsychographyUpdatedDTO(entity);
    }

    @Transactional
    public PsychographyInsertDTO insert(PsychographyInsertDTO dto) {        

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName(); 
        User user = userRepository.findByEmail(login);

        Psychography entity = new Psychography();
        copyDtoToEntity(dto, entity);

        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        String text = entity.getDate().format(formatters);
        LocalDate parsedDate = LocalDate.parse(text, formatters);
        entity.setDate(parsedDate);

        entity.setUser(user);
        entity = repository.save(entity);
        return new PsychographyInsertDTO(entity);
    }    

    @Transactional
    public PsychographyUpdatedDTO update(Long id, PsychographyUpdatedDTO dto) {
        try {
            Psychography entity = repository.getOne(id);
                copyDtoToEntity(dto, entity);

                DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd/MM/uuuu");
                String text = entity.getDate().format(formatters);
                LocalDate parsedDate = LocalDate.parse(text, formatters);
                entity.setDate(parsedDate);

                entity = repository.save(entity);
                return new PsychographyUpdatedDTO(entity);
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

    private void copyDtoToEntity(PsychographyDTO dto, Psychography entity) {
                
        entity.setFullName(dto.getFullName()); 
        entity.setTitle(dto.getTitle());
        entity.setText(dto.getText());       
        entity.setDate(dto.getDate());
        Medium medium = mediumRepository.getOne(dto.getMedium().getId());
        entity.setMedium(medium);  
        entity.setStatus(dto.getStatus());         
    }    
}
