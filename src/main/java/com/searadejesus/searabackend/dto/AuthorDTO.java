package com.searadejesus.searabackend.dto;

import java.io.Serializable;

import com.searadejesus.searabackend.entities.Author;

public class AuthorDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String firstName;
    private String lastName;

    public AuthorDTO() {
    }

    public AuthorDTO(Long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public AuthorDTO(Author entity) {
        id = entity.getId();
        firstName = entity.getFirstName();
        lastName = entity.getLastName();
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
}
