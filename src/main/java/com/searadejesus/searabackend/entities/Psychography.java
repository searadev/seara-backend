package com.searadejesus.searabackend.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_psychography")
public class Psychography implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant moment;

    @Column(columnDefinition = "LONGTEXT")
    private String text;
    private String motherName;
    private String fatherName;
    private String wifeName;
    private String husbandName;
    private String daughterName;
    private String sonName;    
    

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Psychography() {
    }

    public Psychography(Long id, String firstName, String lastName, Instant moment, String text, String motherName, String fatherName, String wifeName, String husbandName, String daughterName, String sonName, Author author, User user) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.moment = moment;
        this.text = text;
        this.author = author;
        this.user = user;
        this.motherName = motherName;
        this.fatherName = fatherName;
        this.wifeName = wifeName;
        this.husbandName = husbandName;
        this.daughterName = daughterName;
        this.sonName = sonName;
        this.author = author;
        this.user = user;
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


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Psychography)) {
            return false;
        }
        Psychography psychography = (Psychography) o;
        return Objects.equals(id, psychography.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }    
}
