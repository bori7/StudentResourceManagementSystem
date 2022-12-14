package com.ecobank.srms.dto;


import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class AdminCreateCourseRequest {

    @NotEmpty(message = "Course Code cannot be empty")
    @Size(min = 4,message = "Course Code must be more than 4")
    private String course_code;

    @NotEmpty(message = "Name of Course cannot be empty")
    @Size(min = 4,message = "Name of Course must be more than 4")
    private String nameOfCourse;

    @NotEmpty(message = "course Description cannot be empty")
    @Size(min = 6,message = "Course Description must be more than 6")
    private String course_Descr;

    @NotEmpty(message = "length of course cannot be empty")
    @Size(min = 3,message = "length of course must be more than 3")
    private String len_course;

    @NotEmpty(message = "Unit of course cannot be empty")
    private String unit_of_course;

    @NotEmpty(message = "status of course cannot be empty")
    private String status_course;

    @NotEmpty(message = "Department Name cannot be empty")
    private String departmentname;

}
