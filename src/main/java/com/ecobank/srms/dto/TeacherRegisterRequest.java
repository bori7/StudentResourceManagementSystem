package com.ecobank.srms.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class TeacherRegisterRequest {
    private String username;
    private String password;
    private String email;
    private String confirmPassword;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Date date_Created;

}
