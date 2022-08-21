package com.searadejesus.searabackend.dto;
import com.searadejesus.searabackend.entities.Class;

public class ClassInsertDTO extends ClassDTO {
    private static final long serialVersionUID = 1L;

    private UserDTO user;

    public ClassInsertDTO() {
        super();
    }
    
    public ClassInsertDTO(Class entity) {        
        
        this.user = new UserDTO(entity.getUser());
    }
    
    public UserDTO getUser() {
        return this.user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
    
}
