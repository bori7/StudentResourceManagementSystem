package com.ecobank.srms.dto;


import lombok.Data;

@Data
public class AdminCreateCourseRequest {

    private String course_code;
    private String nameOfCourse;
    private String course_Descr;
    private String len_course;
    private String unit_of_course;
    private String status_course;
    private String departmentname;

}
