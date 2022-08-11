package com.searadejesus.searabackend.dto;

import java.io.Serializable;
import java.time.Instant;

import com.searadejesus.searabackend.entities.Author;
import com.searadejesus.searabackend.entities.User;
import com.searadejesus.searabackend.entities.Psychography;

public class PsychographyDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String firstName;
    private String lastName;
    private Instant moment;
    private String text;
    private String motherName;
    private String fatherName;
    private String wifeName;
    private String husbandName;
    private String daughterName;
    private String sonName;
    private Author author;
    private User user;

    public PsychographyDTO() {
    }

    public PsychographyDTO(Long id, String firstName, String lastName, Instant moment, String text, String motherName, String fatherName, String wifeName, String husbandName, String daughterName, String sonName, Author author, User user) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.moment = moment;
        this.text = text;
        this.motherName = motherName;
        this.fatherName = fatherName;
        this.wifeName = wifeName;
        this.husbandName = husbandName;
        this.daughterName = daughterName;
        this.sonName = sonName;
        this.author = author;
        this.user = user;
    }

    public PsychographyDTO(Psychography entity){
        id = entity.getId();
        firstName = entity.getFirstName();
        lastName = entity.getLastName();
        moment = entity.getMoment();
        text = entity.getText();
        motherName = entity.getMotherName();
        fatherName = entity.getFatherName();
        wifeName = entity.getWifeName();
        husbandName = entity.getHusbandName();
        daughterName = entity.getDaughterName();
        sonName = entity.getSonName();
        author = entity.getAuthor();
        user = entity.getUser();
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

    public Instant getMoment() {
        return this.moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
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


    public Author getAuthor() {
        return this.author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }   
        
}
