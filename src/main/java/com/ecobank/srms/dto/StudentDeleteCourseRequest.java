package com.ecobank.srms.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class StudentDeleteCourseRequest {
//    @JsonProperty(value = "JambNo")
    private String jambNo;
    private  Long course_Id;
}
