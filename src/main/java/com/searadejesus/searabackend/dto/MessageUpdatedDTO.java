package com.searadejesus.searabackend.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.searadejesus.searabackend.entities.Message;

public class MessageUpdatedDTO extends MessageDTO {
    private static final long serialVersionUID = 1L;

    private Long id;
    @NotBlank(message = "Campo obrigatório")
    private String fullName;
    @NotBlank(message = "Campo obrigatório")
    private String title;
    @NotBlank(message = "Campo obrigatório")
    private String text;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @PastOrPresent(message = "A data não pode estar no futuro")
    private LocalDate date;  

    private MediumDTO medium;

    private Boolean status;    

    public MessageUpdatedDTO() {
        super();
    }

    public MessageUpdatedDTO(Long id, String fullName, String title, String text, LocalDate date, MediumDTO medium, Boolean status) {
        this.id = id;
        this.fullName = fullName;
        this.title = title;
        this.text = text;
        this.date = date;
        this.medium = medium;
        this.status = status;
    }

    public MessageUpdatedDTO(Message entity){
        id = entity.getId();
        text = entity.getText();
        fullName = entity.getFullName();
        title = entity.getTitle();
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

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
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
