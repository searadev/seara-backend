package com.searadejesus.searabackend.controllers;
import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.searadejesus.searabackend.dto.ModuleDTO;
import com.searadejesus.searabackend.services.ModuleService;

@RestController
@RequestMapping(value = "/modules")
public class ModuleController {
    
    @Autowired
    private ModuleService service;

    @GetMapping
    public ResponseEntity<Page<ModuleDTO>> findAll(Pageable pageable) {
        Page<ModuleDTO> list = service.findAllPaged(pageable);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ModuleDTO> findById(@PathVariable Long id) {
        ModuleDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<ModuleDTO> insert(@Valid @RequestBody ModuleDTO dto) {
        ModuleDTO newDto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newDto.getId()).toUri();
        return ResponseEntity.created(uri).body(newDto);

    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ModuleDTO> update(@Valid @PathVariable Long id, @RequestBody ModuleDTO dto){
        dto = service.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
