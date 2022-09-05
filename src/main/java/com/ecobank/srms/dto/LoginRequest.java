package com.ecobank.srms.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String UserName;
    private String password;
}
