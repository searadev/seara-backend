package com.searadejesus.searabackend.dto;

import com.searadejesus.searabackend.entities.Psychography;

public class PsychographyInsertDTO extends PsychographyDTO {
    private static final long serialVersionUID = 1L;
    
    private UserDTO user;

    public PsychographyInsertDTO() {
        super();
    }
    
    public PsychographyInsertDTO(Psychography entity) {
        this.user = new UserDTO(entity.getUser());
    } 

    public UserDTO getUser() {
        return this.user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }   
        
}
