package com.ecobank.srms.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;


@Data
public class AdminRegisterRequest {
    private String username;
    private String password;
    private String fName;
    private String confirmPassword;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Date date_Created;

}
