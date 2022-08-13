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

import com.searadejesus.searabackend.dto.MessageDTO;
import com.searadejesus.searabackend.dto.MessageInsertDTO;
import com.searadejesus.searabackend.entities.Message;
import com.searadejesus.searabackend.entities.User;
import com.searadejesus.searabackend.repositories.MessageRepository;
import com.searadejesus.searabackend.repositories.UserRepository;
import com.searadejesus.searabackend.services.exceptions.DataBaseException;
import com.searadejesus.searabackend.services.exceptions.ResourceNotFoundException;

@Service
public class MessageService {

    @Autowired
    private MessageRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public Page<MessageDTO> findAllPaged(Pageable pageable) {
        Page<Message> list = repository.findAll(pageable);
        return list.map(x -> new MessageDTO(x));
    }

    @Transactional(readOnly = true)
    public MessageDTO findById(Long id) {
        Optional<Message> obj = repository.findById(id);
        Message entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new MessageDTO(entity);
    }

    @Transactional
    public MessageDTO insert(MessageInsertDTO dto) {
        Message entity = new Message();
        copyDtoToEntity(dto, entity);
        User user = userRepository.getOne(dto.getUser().getId());
        entity.setUser(user);
        entity = repository.save(entity);
        return new MessageDTO(entity);
    }    

    @Transactional
    public MessageDTO update(Long id, MessageDTO dto) {
        try {
            Message entity = repository.getOne(id);
                copyDtoToEntity(dto, entity);
                entity = repository.save(entity);
                return new MessageDTO(entity);
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

        entity.setText(dto.getText());       
        
    }
    
}
