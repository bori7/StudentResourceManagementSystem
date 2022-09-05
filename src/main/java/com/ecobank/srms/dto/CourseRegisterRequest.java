package com.ecobank.srms.dto;

import lombok.Data;

import java.util.List;

@Data
public class CourseRegisterRequest {
    private String regNo;
    private List<Long> courses;
}
