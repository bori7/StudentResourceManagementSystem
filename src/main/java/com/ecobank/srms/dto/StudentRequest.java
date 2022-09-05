package com.ecobank.srms.dto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import javax.validation.constraints.NotEmpty;

import javax.validation.constraints.Size;
import java.util.Date;
@Data
public class StudentRequest {
    @NotEmpty(message = "Registration NO cannot be empty")
    @Size(min = 6, message = "Registration NO cannot be less than 5")
    private String regNo;
    @NotEmpty(message = "Level cannot be empty")
    @Size(min = 2, max = 5, message = "Invalid Level")
    private String level;

    @NotEmpty(message = "Department cannot be empty")
    @Size(min = 4, message = "Enter Valid Department")
    private String department;

    @NotEmpty(message = "Last Name cannot be empty")
    @Size(min = 1,  message = "Last Name should not be less than 1")
    private String lastName;

    @NotEmpty(message = "First Name cannot be empty")
    @Size(min = 2, message = "First Name should be more than 2")
    private String firstName;

    @NotEmpty(message = "Username cannot be empty")
    @Size(min = 4,message = "Username must be more than 4")
    private String userName;

    @NotEmpty(message = "Password cannot be empty")
    @Size(min = 8, message = "Password must be more than 8 characters")
    private String password;

    @NotEmpty(message = "Department doesn't exist")
    private Long dept_Id;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Date date_Created;
}

