package com.searadejesus.searabackend.dto;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.searadejesus.searabackend.entities.Lecture;

public class LectureDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    @NotBlank(message = "Campo obrigatório")
    private String title;
    @NotBlank(message = "Campo obrigatório")
    private String url;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern="dd/MM/yyyy")
    @PastOrPresent(message = "A data não pode ser futura")
    private LocalDate date;   
    
    private MediumDTO medium;

    public LectureDTO() {
    }

    public LectureDTO(Long id, String title, String url, LocalDate date, MediumDTO medium) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.date = date;
        this.medium = medium;
    }    

    public LectureDTO(Lecture entity){
        id = entity.getId();
        title = entity.getTitle();
        url = entity.getUrl();
        date = entity.getDate();
        this.medium = new MediumDTO(entity.getMedium());
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
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
}
