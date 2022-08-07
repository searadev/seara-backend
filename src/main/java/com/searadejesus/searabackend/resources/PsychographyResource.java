package com.searadejesus.searabackend.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.searadejesus.searabackend.dto.PsychographyDTO;
import com.searadejesus.searabackend.services.PsychographyService;

@RestController
@RequestMapping(value = "/psychographys")
public class PsychographyResource {
    
    @Autowired
    private PsychographyService service;

    @GetMapping
    public ResponseEntity<Page<PsychographyDTO>> findAll(Pageable pageable) {
        Page<PsychographyDTO> list = service.findAllPaged(pageable);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PsychographyDTO> findById(@PathVariable Long id) {
        PsychographyDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);

    }
}
