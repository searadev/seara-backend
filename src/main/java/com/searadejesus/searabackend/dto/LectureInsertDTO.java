package com.searadejesus.searabackend.dto;
import com.searadejesus.searabackend.entities.Lecture;

public class LectureInsertDTO extends LectureDTO {
    private static final long serialVersionUID = 1L;

    private UserDTO user;

    public LectureInsertDTO() {
        super();
    }
    
    public LectureInsertDTO(Lecture entity) {        
        
        this.user = new UserDTO(entity.getUser());
    }
    
    public UserDTO getUser() {
        return this.user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
    
}
