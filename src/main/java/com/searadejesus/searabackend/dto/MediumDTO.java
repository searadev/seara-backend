package com.searadejesus.searabackend.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import com.searadejesus.searabackend.entities.Medium;

public class MediumDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    @NotBlank(message = "Campo obrigatório")
    private String fullName;

    public MediumDTO() {
    }

    public MediumDTO(Long id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }

    public MediumDTO(Medium entity) {
        id = entity.getId();
        fullName = entity.getFullName();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
     
}
