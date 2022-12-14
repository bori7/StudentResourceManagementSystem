package com.ecobank.srms.dto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
@Data
public class StudentRequest {



    @NotEmpty(message = "Level cannot be empty")
    @NotNull(message = "Level cannot be null")
    @Size(min = 2, max = 5, message = "Invalid Level")
    private String level;

    @NotEmpty(message = "Department cannot be empty")
    @Size(min = 2, message = "Enter Valid Department")
    private String department;

    @NotEmpty(message = "jambNo cannot be empty")
    @Size(min = 4,message = "jambNo must be more than 4")
    private String jambNo;

    @NotEmpty(message = "Password cannot be empty")
    @Size(min = 8, message = "Password must be more than 8 characters")
    private String password;

//    @NotEmpty(message = "Department doesn't exist")
    @JsonProperty(value = "dept_id")
    private Long dept_id;

    @NotEmpty
    private String confirmPassword;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Date date_Created;

    @Email
    private String email;
}

