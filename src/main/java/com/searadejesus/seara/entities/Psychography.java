package com.searadejesus.seara.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

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
    private Instant moment;
    private String text;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Psychography() {
    }

    public Psychography(Long id, String firstName, String lastName, Instant moment, String text, User user) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.moment = moment;
        this.text = text;
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
