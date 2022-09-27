package com.ecobank.srms.dto;


import lombok.Data;

@Data
public class StudentDeleteCourseRequest {
    private String JambNo;
    private  Long course_Id;
}
