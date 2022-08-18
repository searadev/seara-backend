package com.searadejesus.searabackend.resources;
import java.net.URI;

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

import com.searadejesus.searabackend.dto.LectureDTO;
import com.searadejesus.searabackend.dto.LectureInsertDTO;
import com.searadejesus.searabackend.services.LectureService;

@RestController
@RequestMapping(value = "/lectures")
public class LectureResource {
    
    @Autowired
    private LectureService service;

    @GetMapping
    public ResponseEntity<Page<LectureDTO>> findAll(Pageable pageable) {
        Page<LectureDTO> list = service.findAllPaged(pageable);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<LectureDTO> findById(@PathVariable Long id) {
        LectureDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<LectureDTO> insert(@RequestBody LectureInsertDTO dto) {
        LectureDTO newDto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newDto.getId()).toUri();
        return ResponseEntity.created(uri).body(newDto);

    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<LectureDTO> update(@PathVariable Long id, @RequestBody LectureDTO dto){
        dto = service.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}