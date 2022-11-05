package com.searadejesus.searabackend.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.searadejesus.searabackend.dto.RoleDTO;
import com.searadejesus.searabackend.entities.Role;
import com.searadejesus.searabackend.repositories.RoleRepository;
import com.searadejesus.searabackend.services.exceptions.ResourceNotFoundException;

@Service
public class RoleService {
    
    @Autowired
    private RoleRepository repository;

    @Transactional(readOnly = true)
    public Page<RoleDTO> findAll(Pageable pageable) {
        Page<Role> list = repository.findAll(pageable);
        return list.map(x -> new RoleDTO(x));
    }

    @Transactional(readOnly = true)
    public RoleDTO findById(Long id) {
        Optional<Role> obj = repository.findById(id);
        Role entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new RoleDTO(entity);
    }
    
}
