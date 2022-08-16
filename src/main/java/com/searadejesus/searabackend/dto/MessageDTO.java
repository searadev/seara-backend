package com.searadejesus.searabackend.dto;

import java.io.Serializable;

import com.searadejesus.searabackend.entities.Message;
import com.searadejesus.searabackend.entities.User;

public class MessageDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String text;

    public MessageDTO() {
    }

    public MessageDTO(Long id, String text, User user) {
        this.id = id;
        this.text = text;
    }

    public MessageDTO(Message entity){
        id = entity.getId();
        text = entity.getText();
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
}
