package com.ecobank.srms.dto;

import lombok.Data;

@Data
public class AdminChangePasswordRequest {
    private String username;
    private String currentPassword;
    private String newPassword;
    private String confirmPassword;
}


