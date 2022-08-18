package com.searadejesus.searabackend.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import com.searadejesus.searabackend.entities.Module;

public class ModuleDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    @NotBlank(message = "Campo obrigatório")
    private String name; 

    public ModuleDTO() {
    }

    public ModuleDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public ModuleDTO(Module entity) {
        id = entity.getId();
        name = entity.getName();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
