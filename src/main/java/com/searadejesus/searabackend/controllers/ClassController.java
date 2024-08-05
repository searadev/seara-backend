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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.searadejesus.searabackend.dto.ClassDTO;
import com.searadejesus.searabackend.dto.ClassInsertDTO;
import com.searadejesus.searabackend.dto.ClassUpdatedDTO;
import com.searadejesus.searabackend.services.ClassService;

@RestController
@RequestMapping(value = "/classes")
public class ClassController {
    
    @Autowired
    private ClassService service;

    @GetMapping
    public ResponseEntity<Page<ClassDTO>> findAll(
        Pageable pageable,
        @RequestParam(value = "title", defaultValue = "") String title,
        @RequestParam(value = "moduleId", defaultValue = "0") Long moduleId
        ) {
        Page<ClassDTO> list = service.findAllPaged(pageable, title.trim(), moduleId); 
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClassDTO> findById(@PathVariable Long id) {
        ClassDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<ClassDTO> insert(@Valid @RequestBody ClassInsertDTO dto) {
        ClassDTO newDto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newDto.getId()).toUri();
        return ResponseEntity.created(uri).body(newDto);

    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ClassDTO> update(@Valid @PathVariable Long id, @RequestBody ClassUpdatedDTO dto){
        ClassDTO newDto = service.update(id, dto);
        return ResponseEntity.ok().body(newDto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
