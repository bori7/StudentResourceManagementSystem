package com.ecobank.srms.dto;

import lombok.Data;

import java.util.List;

@Data
public class CourseRegisterRequest {
    private String jambNo;
    private List<Long> courses;
}
