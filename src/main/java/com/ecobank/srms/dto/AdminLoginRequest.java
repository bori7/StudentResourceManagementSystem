package com.ecobank.srms.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class AdminLoginRequest {
    @NotEmpty(message = "jambNo cannot be empty")
    @Size(min = 4,message = "jambNo must be more than 4")
    private String username;

    @NotEmpty(message = "Password cannot be empty")
    @Size(min = 8, message = "Password must be more than 8 characters")
    private String password;

}
