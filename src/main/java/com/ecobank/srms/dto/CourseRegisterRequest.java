package com.ecobank.srms.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class CourseRegisterRequest {
    @NotEmpty(message = "Password cannot be empty")
    @Size(min = 8, message = "Password must be more than 8 characters")
    private String jambNo;

    @NotNull(message = " Courses should not be empty")
    private List<Long> courses;
}
