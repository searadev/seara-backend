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

import com.searadejesus.searabackend.dto.PsychographyDTO;
import com.searadejesus.searabackend.dto.PsychographyInsertDTO;
import com.searadejesus.searabackend.dto.PsychographyPagedDTO;
import com.searadejesus.searabackend.dto.PsychographyUpdatedDTO;
import com.searadejesus.searabackend.services.PsychographyService;

@RestController
@RequestMapping(value = "/psychographies")
public class PsychographyController {
    
    @Autowired
    private PsychographyService service;

    @GetMapping
    public ResponseEntity<Page<PsychographyPagedDTO>> findAll(
        Pageable pageable,
        @RequestParam(value = "fullname", defaultValue = "") String fullName,
        @RequestParam(value = "text", defaultValue = "") String text
        ) {
        Page<PsychographyPagedDTO> list = service.findAllPaged(pageable, fullName.trim(), text);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<Page<PsychographyPagedDTO>> findAllStatus(
        Pageable pageable,
        @RequestParam(value = "fullname", defaultValue = "") String fullName,
        @RequestParam(value = "text", defaultValue = "") String text
        ) {
        Page<PsychographyPagedDTO> list = service.findAllStatus(pageable, fullName.trim(), text);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PsychographyDTO> findById(@PathVariable Long id) {
        PsychographyDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping(value = "/admin/{id}")
    public ResponseEntity<PsychographyUpdatedDTO> findByIdAdmin(@PathVariable Long id) {
        PsychographyUpdatedDTO dto = service.findByIdAdmin(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<PsychographyInsertDTO> insert(@Valid @RequestBody PsychographyInsertDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);

    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PsychographyUpdatedDTO> update(@Valid @PathVariable Long id, @RequestBody PsychographyUpdatedDTO dto){
        dto = service.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
