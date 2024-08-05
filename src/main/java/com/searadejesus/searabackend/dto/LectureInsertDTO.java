package com.searadejesus.searabackend.dto;
import java.time.LocalDate;

import javax.validation.constraints.PastOrPresent;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.searadejesus.searabackend.entities.Lecture;

public class LectureInsertDTO extends LectureDTO {
    private static final long serialVersionUID = 1L;

    private UserDTO user;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern="yyyy-MM-dd")
    @PastOrPresent(message = "A data não pode estar no futuro")
    private LocalDate date; 

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
