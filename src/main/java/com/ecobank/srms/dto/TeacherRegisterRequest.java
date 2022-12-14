package com.ecobank.srms.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class TeacherRegisterRequest {
    @NotEmpty(message = "UserName cannot be empty")
    @Size(min = 4,message = "UserName must be more than 4")
    private String username;

    @NotEmpty(message = "Password cannot be empty")
    @Size(min = 8, message = "Password must be more than 8 characters")
    private String password;

    @Email
    private String email;

    @NotEmpty(message = "Password cannot be empty")
    @Size(min = 8, message = "Password must be more than 8 characters")
    private String confirmPassword;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Date date_Created;
}
