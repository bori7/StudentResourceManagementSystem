package com.ecobank.srms.dto;


import lombok.Data;

@Data
public class AdminResetPasswordRequest {
    private String username;
    private String newPassword;
    private String confirmPassword;
}
