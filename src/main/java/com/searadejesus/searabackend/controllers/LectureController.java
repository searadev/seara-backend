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

import com.searadejesus.searabackend.dto.LectureDTO;
import com.searadejesus.searabackend.dto.LectureInsertDTO;
import com.searadejesus.searabackend.dto.LectureUpdatedDTO;
import com.searadejesus.searabackend.services.LectureService;

@RestController
@RequestMapping(value = "/lectures")
public class LectureController {
    
    @Autowired
    private LectureService service;

    @GetMapping
    public ResponseEntity<Page<LectureDTO>> findAll(
        Pageable pageable,
        @RequestParam(value = "title", defaultValue = "") String title
        ) {
        Page<LectureDTO> list = service.findAllPaged(pageable, title.trim());
        return ResponseEntity.ok().body(list);
    }    

    @GetMapping(value = "/{id}")
    public ResponseEntity<LectureDTO> findById(@PathVariable Long id) {
        LectureDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping(value = "/admin/{id}")
    public ResponseEntity<LectureUpdatedDTO> findByIdAdmin(@PathVariable Long id) {
        LectureUpdatedDTO dto = service.findByIdAdmin(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<LectureInsertDTO> insert(@Valid @RequestBody LectureInsertDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);

    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<LectureUpdatedDTO> update(@Valid @PathVariable Long id, @RequestBody LectureUpdatedDTO dto){
        dto = service.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
