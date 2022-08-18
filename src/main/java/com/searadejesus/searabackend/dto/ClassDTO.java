package com.searadejesus.searabackend.dto;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.searadejesus.searabackend.entities.Class;
import com.searadejesus.searabackend.entities.User;

public class ClassDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String title;
    private String uri;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern="dd/MM/yyyy")
    //@DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate date;

    private ModuleDTO module;
    
    private MediumDTO medium;

    public ClassDTO() {
    }

    public ClassDTO(Long id, String title, String uri, LocalDate date, ModuleDTO module, MediumDTO medium) {
        this.id = id;
        this.title = title;
        this.uri = uri;
        this.date = date;
        this.module = module;
        this.medium = medium;
    }    

    public ClassDTO(Class entity){
        id = entity.getId();
        title = entity.getTitle();
        uri = entity.getUri();
        date = entity.getDate();
        this.module = new ModuleDTO(entity.getModule());
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

    public String getUri() {
        return this.uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public ModuleDTO getModule() {
        return this.module;
    }

    public void setModule(ModuleDTO module) {
        this.module = module;
    }

    public MediumDTO getMedium() {
        return this.medium;
    }

    public void setMedium(MediumDTO medium) {
        this.medium = medium;
    }    
}
