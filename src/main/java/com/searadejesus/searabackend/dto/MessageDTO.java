package com.searadejesus.searabackend.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;

import com.searadejesus.searabackend.entities.Message;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;

public class MessageDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    @NotBlank(message = "Campo obrigatório")
    private String fullName;
    @NotBlank(message = "Campo obrigatório")
    private String text;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern="dd/MM/yyyy")
    @PastOrPresent(message = "A data não pode estar no futuro")
    private LocalDate date;    

    private MediumDTO medium;

    private Boolean status;

    public MessageDTO() {
    }

    public MessageDTO(Long id, String fullName, String text, LocalDate date, MediumDTO medium, Boolean status) {
        this.id = id;
        this.fullName = fullName;
        this.text = text;
        this.date = date;
        this.medium = medium;
        this.status = status;
    }          

    public MessageDTO(Message entity){
        id = entity.getId();
        text = entity.getText();
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

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
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
