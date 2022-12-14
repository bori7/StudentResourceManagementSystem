package com.ecobank.srms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class LoginRequest {

    @NotEmpty(message = "Username cannot be empty")
    @Size(min = 4,message = "Username must be more than 4")
    @Schema(example = "20809086")
    private String jambNo;

    @Size(min = 8, message = "Password must be more than 8 characters")
    @Schema(example = "20809086")
    private String password;
}
