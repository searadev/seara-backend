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

import com.searadejesus.searabackend.dto.LectureDTO;
import com.searadejesus.searabackend.dto.LectureInsertDTO;
import com.searadejesus.searabackend.dto.LectureUpdatedDTO;
import com.searadejesus.searabackend.entities.Lecture;
import com.searadejesus.searabackend.entities.User;
import com.searadejesus.searabackend.repositories.LectureRepository;
import com.searadejesus.searabackend.repositories.MediumRepository;
import com.searadejesus.searabackend.repositories.UserRepository;
import com.searadejesus.searabackend.services.exceptions.DataBaseException;
import com.searadejesus.searabackend.services.exceptions.ResourceNotFoundException;
import com.searadejesus.searabackend.entities.Medium;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Service
public class LectureService {

    @Autowired
    private LectureRepository repository;

    @Autowired
    private MediumRepository mediumRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public Page<LectureDTO> findAllPaged(Pageable pageable, String title) {
        Page<Lecture> list = repository.findPageable(title, pageable);
        return list.map(x -> new LectureDTO(x));
    }

    @Transactional(readOnly = true)
    public LectureDTO findById(Long id) {
        Optional<Lecture> obj = repository.findById(id);
        Lecture entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new LectureDTO(entity);
    }

    @Transactional(readOnly = true)
    public LectureUpdatedDTO findByIdAdmin(Long id) {
        Optional<Lecture> obj = repository.findById(id);
        Lecture entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new LectureUpdatedDTO(entity);
    }

    @Transactional
    public LectureInsertDTO insert(LectureInsertDTO dto) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName(); 
        User user = userRepository.findByEmail(login);

        Lecture entity = new Lecture();
        copyDtoToEntity(dto, entity);

        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        String text = entity.getDate().format(formatters);
        LocalDate parsedDate = LocalDate.parse(text, formatters);
        entity.setDate(parsedDate);

        entity.setUser(user);
        entity = repository.save(entity);
        return new LectureInsertDTO(entity);
    }    

    @Transactional
    public LectureUpdatedDTO update(Long id, LectureUpdatedDTO dto) {
        try {
            Lecture entity = repository.getOne(id);
                copyDtoToEntity(dto, entity);

                DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd/MM/uuuu");
                String text = entity.getDate().format(formatters);
                LocalDate parsedDate = LocalDate.parse(text, formatters);
                entity.setDate(parsedDate);

                entity = repository.save(entity);
                return new LectureUpdatedDTO(entity);
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

    private void copyDtoToEntity(LectureDTO dto, Lecture entity) {      

        entity.setDate(dto.getDate());
        entity.setTitle(dto.getTitle());
        entity.setUrl(dto.getUrl());
        
        Medium medium = mediumRepository.getOne(dto.getMedium().getId());
        entity.setMedium(medium);
        
    }
    
}
