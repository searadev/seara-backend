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

import com.searadejesus.searabackend.dto.MediumDTO;
import com.searadejesus.searabackend.services.MediumService;


@RestController
@RequestMapping(value = "/mediuns")
public class MediumResource {

    @Autowired
    private MediumService service;

    @GetMapping
    public ResponseEntity<Page<MediumDTO>> findAll(Pageable pageable) {
        Page<MediumDTO> list = service.findAllPaged(pageable);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<MediumDTO> findById(@PathVariable Long id) {
        MediumDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<MediumDTO> insert(@RequestBody MediumDTO dto) {
        MediumDTO newDto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newDto.getId()).toUri();
        return ResponseEntity.created(uri).body(newDto);

    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<MediumDTO> update(@PathVariable Long id, @RequestBody MediumDTO dto){
        dto = service.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }    
}
