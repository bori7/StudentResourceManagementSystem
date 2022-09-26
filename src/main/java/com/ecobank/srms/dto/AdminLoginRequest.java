package com.ecobank.srms.dto;

import lombok.Data;

@Data
public class AdminLoginRequest {
    private String username;
    private String password;
}
