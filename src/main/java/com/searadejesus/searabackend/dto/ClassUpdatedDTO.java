package com.searadejesus.searabackend.dto;
import java.time.LocalDate;

import javax.validation.constraints.PastOrPresent;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ClassUpdatedDTO extends ClassDTO {
    private static final long serialVersionUID = 1L;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern="yyyy-MM-dd")
    @PastOrPresent(message = "A data não pode estar no futuro")
    private LocalDate date; 

    public ClassUpdatedDTO() {
        super();
    }
    
}
