package com.ecobank.srms.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class StudentDeleteCourseRequest {
    @NotNull(message = "JambNo cannot be null")
    @Size(min = 4,message = "jambNo must be more than 4")
    private String jambNo;

    @NotNull(message = " Course should not be empty")
    private  Long course_Id;
}
