package com.ecobank.srms.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class AdminChangePasswordRequest {
    @NotEmpty(message = "jambNo cannot be empty")
    @Size(min = 4,message = "jambNo must be more than 4")
    private String username;

    @NotEmpty(message = "Current Password cannot be empty")
    @Size(min = 8, message = "Current Password must be more than 8 characters")
    private String currentPassword;

    @NotEmpty(message = "New Password cannot be empty")
    @Size(min = 8, message = "New Password must be more than 8 characters")
    private String newPassword;

    @NotEmpty(message = "Confirm Password cannot be empty")
    @Size(min = 8, message = "Confirm Password must be more than 8 characters")
    private String confirmPassword;
}


