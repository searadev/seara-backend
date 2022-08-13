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

import com.searadejesus.searabackend.dto.PsychographyDTO;
import com.searadejesus.searabackend.dto.PsychographyInsertDTO;
import com.searadejesus.searabackend.entities.Author;
import com.searadejesus.searabackend.entities.Psychography;
import com.searadejesus.searabackend.entities.User;
import com.searadejesus.searabackend.repositories.AuthorRepository;
import com.searadejesus.searabackend.repositories.PsychographyRepository;
import com.searadejesus.searabackend.repositories.UserRepository;
import com.searadejesus.searabackend.services.exceptions.DataBaseException;
import com.searadejesus.searabackend.services.exceptions.ResourceNotFoundException;

@Service
public class PsychographyService {

    @Autowired
    private PsychographyRepository repository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private UserRepository userRepository;

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

    @Transactional
    public PsychographyDTO insert(PsychographyInsertDTO dto) {
        Psychography entity = new Psychography();
        copyDtoToEntity(dto, entity);
        User user = userRepository.getOne(dto.getUser().getId());
        entity.setUser(user);
        entity = repository.save(entity);
        return new PsychographyDTO(entity);
    }    

    @Transactional
    public PsychographyDTO update(Long id, PsychographyDTO dto) {
        try {
            Psychography entity = repository.getOne(id);
                copyDtoToEntity(dto, entity);
                entity = repository.save(entity);
                return new PsychographyDTO(entity);
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
        
        entity.setDaughterName(dto.getDaughterName());
        entity.setFatherName(dto.getFatherName());
        entity.setFirstName(dto.getFirstName());
        entity.setHusbandName(dto.getHusbandName());
        entity.setLastName(dto.getLastName());
        entity.setDate(dto.getDate());
        entity.setMotherName(dto.getMotherName());
        entity.setSonName(dto.getSonName());
        entity.setText(dto.getText());
        entity.setWifeName(dto.getWifeName());

        Author author = authorRepository.getOne(dto.getAuthor().getId());
        entity.setAuthor(author);       
        
    }    
}
