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

import com.searadejesus.searabackend.dto.MessageDTO;
import com.searadejesus.searabackend.dto.MessageInsertDTO;
import com.searadejesus.searabackend.dto.MessagePagedDTO;
import com.searadejesus.searabackend.dto.MessageUpdatedDTO;
import com.searadejesus.searabackend.entities.Medium;
import com.searadejesus.searabackend.entities.Message;
import com.searadejesus.searabackend.entities.User;
import com.searadejesus.searabackend.repositories.MediumRepository;
import com.searadejesus.searabackend.repositories.MessageRepository;
import com.searadejesus.searabackend.repositories.UserRepository;
import com.searadejesus.searabackend.services.exceptions.DataBaseException;
import com.searadejesus.searabackend.services.exceptions.ResourceNotFoundException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Service
public class MessageService {

    @Autowired
    private MessageRepository repository;

    @Autowired
    private MediumRepository mediumRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public Page<MessagePagedDTO> findAllPaged(Pageable pageable, String fullName, String text) {
        Page<Message> list = repository.find(pageable, fullName, text);
        return list.map(x -> new MessagePagedDTO(x));
    }

    @Transactional(readOnly = true)
    public Page<MessageDTO> findAllStatus(Pageable pageable, String fullName, String text) {
        Page<Message> list = repository.findAllStatus(pageable, fullName, text);
        return list.map(x -> new MessageDTO(x));
    }

    @Transactional(readOnly = true)
    public MessageDTO findById(Long id) {
        Optional<Message> obj = repository.findById(id);
        Message entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new MessageDTO(entity);
    }

    @Transactional(readOnly = true)
    public MessageUpdatedDTO findByIdAdmin(Long id) {
        Optional<Message> obj = repository.findById(id);
        Message entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new MessageUpdatedDTO(entity);
    }

    @Transactional
    public MessageInsertDTO insert(MessageInsertDTO dto) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName(); 
        User user = userRepository.findByEmail(login);

        Message entity = new Message();
        copyDtoToEntity(dto, entity);

        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        String text = entity.getDate().format(formatters);
        LocalDate parsedDate = LocalDate.parse(text, formatters);
        entity.setDate(parsedDate);

        entity.setUser(user);
        
        entity = repository.save(entity);
        return new MessageInsertDTO(entity);
    }    

    @Transactional
    public MessageUpdatedDTO update(Long id, MessageUpdatedDTO dto) {
        try {
            Message entity = repository.getOne(id);
                copyDtoToEntity(dto, entity);

                DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd/MM/uuuu");
                String text = entity.getDate().format(formatters);
                LocalDate parsedDate = LocalDate.parse(text, formatters);
                entity.setDate(parsedDate);

                entity = repository.save(entity);
                return new MessageUpdatedDTO(entity);
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

    private void copyDtoToEntity(MessageDTO dto, Message entity) {

        entity.setFullName(dto.getFullName());
        entity.setTitle(dto.getTitle());
        entity.setText(dto.getText());  
        entity.setDate(dto.getDate());
        Medium medium = mediumRepository.getOne(dto.getMedium().getId());
        entity.setMedium(medium);
        entity.setStatus(dto.getStatus());
    }    
}
