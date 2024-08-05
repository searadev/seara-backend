package com.searadejesus.searabackend.dto;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.searadejesus.searabackend.entities.Class;

public class ClassUpdatedDTO extends ClassDTO {
    private static final long serialVersionUID = 1L;

    private Long id;
    
    @NotBlank(message = "Campo obrigatório")
    private String title;
    @NotBlank(message = "Campo obrigatório")
    private String url;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern="yyyy-MM-dd")
    @PastOrPresent(message = "A data não pode ser futura")
    private LocalDate date;

    private ModuleDTO module;
    
    private MediumDTO medium;

    public ClassUpdatedDTO() {
    }

    public ClassUpdatedDTO(Long id, String title, String url, LocalDate date, ModuleDTO module, MediumDTO medium) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.date = date;
        this.module = module;
        this.medium = medium;
    }    

    public ClassUpdatedDTO(Class entity){
        id = entity.getId();
        title = entity.getTitle();
        url = entity.getUrl();
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
