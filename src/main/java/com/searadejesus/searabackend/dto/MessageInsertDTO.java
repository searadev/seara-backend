package com.searadejesus.searabackend.dto;
import com.searadejesus.searabackend.entities.Message;

public class MessageInsertDTO extends MessageDTO {
    private static final long serialVersionUID = 1L;

    private UserDTO user;

    public MessageInsertDTO() {
        super();
    }
    
    public MessageInsertDTO(Message entity) {        
        
        this.user = new UserDTO(entity.getUser());
    }
    
    public UserDTO getUser() {
        return this.user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
    
}
