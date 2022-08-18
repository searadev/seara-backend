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
    private String firstName;
    private String lastName;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern="dd/MM/yyyy")
    @PastOrPresent(message = "A data não pode estar no futuro")
    private LocalDate date;
    
    @NotBlank(message = "Campo obrigatório")
    private String text;
    private String motherName;
    private String fatherName;
    private String wifeName;
    private String husbandName;
    private String daughterName;
    private String sonName;
    private MediumDTO medium;

    public PsychographyDTO() {
    }

    public PsychographyDTO(Long id, String firstName, String lastName, LocalDate date, String text, String motherName, String fatherName, String wifeName, String husbandName, String daughterName, String sonName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.date = date;
        this.text = text;
        this.motherName = motherName;
        this.fatherName = fatherName;
        this.wifeName = wifeName;
        this.husbandName = husbandName;
        this.daughterName = daughterName;
        this.sonName = sonName;        
    }

    public PsychographyDTO(Psychography entity){
        id = entity.getId();
        firstName = entity.getFirstName();
        lastName = entity.getLastName();
        date = entity.getDate();
        text = entity.getText();
        motherName = entity.getMotherName();
        fatherName = entity.getFatherName();
        wifeName = entity.getWifeName();
        husbandName = entity.getHusbandName();
        daughterName = entity.getDaughterName();
        sonName = entity.getSonName();
        this.medium = new MediumDTO(entity.getMedium());
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public String getMotherName() {
        return this.motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getFatherName() {
        return this.fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getWifeName() {
        return this.wifeName;
    }

    public void setWifeName(String wifeName) {
        this.wifeName = wifeName;
    }

    public String getHusbandName() {
        return this.husbandName;
    }

    public void setHusbandName(String husbandName) {
        this.husbandName = husbandName;
    }

    public String getDaughterName() {
        return this.daughterName;
    }

    public void setDaughterName(String daughterName) {
        this.daughterName = daughterName;
    }

    public String getSonName() {
        return this.sonName;
    }

    public void setSonName(String sonName) {
        this.sonName = sonName;
    }

    public MediumDTO getMedium() {
        return this.medium;
    }

    public void setMedium(MediumDTO medium) {
        this.medium = medium;
    }   
}
