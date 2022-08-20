package com.searadejesus.searabackend.dto;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.searadejesus.searabackend.entities.Psychography;

public class PsychographyDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    @NotBlank(message = "Campo obrigatório")
    private String fullName;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern="dd/MM/yyyy")
    @PastOrPresent(message = "A data não pode estar no futuro")
    private LocalDate date;
    
    @NotBlank(message = "Campo obrigatório")
    private String text;
    private MediumDTO medium;

    public PsychographyDTO() {
    }

    public PsychographyDTO(Long id, String fullName, LocalDate date, String text, MediumDTO medium) {
        this.id = id;
        this.fullName = fullName;
        this.date = date;
        this.text = text;
        this.medium = medium;
    }    

    public PsychographyDTO(Psychography entity){
        id = entity.getId();
        fullName = entity.getFullName();
        date = entity.getDate();
        text = entity.getText();
        this.medium = new MediumDTO(entity.getMedium());
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

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public MediumDTO getMedium() {
        return this.medium;
    }

    public void setMedium(MediumDTO medium) {
        this.medium = medium;
    }       
}
