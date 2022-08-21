package com.searadejesus.searabackend.dto;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.searadejesus.searabackend.entities.Psychography;

public class PsychographyPagedDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    @NotBlank(message = "Campo obrigatório")
    private String fullName;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern="dd/MM/yyyy")
    @PastOrPresent(message = "A data não pode estar no futuro")
    private LocalDate date;    
    
    private MediumDTO medium;

    private Boolean status;

    public PsychographyPagedDTO() {
    }

    public PsychographyPagedDTO(Long id, String fullName, LocalDate date, MediumDTO medium, Boolean status) {
        this.id = id;
        this.fullName = fullName;
        this.date = date;
        this.medium = medium;
        this.status = status;
    }    

    public PsychographyPagedDTO(Psychography entity){
        id = entity.getId();
        fullName = entity.getFullName();
        date = entity.getDate();        
        this.medium = new MediumDTO(entity.getMedium());
        this.status = entity.getStatus();
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

    public LocalDate getDate() {
        return this.date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public MediumDTO getMedium() {
        return this.medium;
    }

    public void setMedium(MediumDTO medium) {
        this.medium = medium;
    }  

    public Boolean isStatus() {
        return this.status;
    }

    public Boolean getStatus() {
        return this.status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
