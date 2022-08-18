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
    private String text;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern="dd/MM/yyyy")
    @PastOrPresent(message = "A data não pode estar no futuro")
    private LocalDate date;

    private String fullName;

    public MessageDTO() {
    }

    public MessageDTO(Long id, String text, LocalDate date, String fullName) {
        this.id = id;
        this.text = text;
        this.date = date;
        this.fullName = fullName;
    }      

    public MessageDTO(Message entity){
        id = entity.getId();
        text = entity.getText();
        date = entity.getDate();
        fullName = entity.getFullName();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
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


    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }    
}
