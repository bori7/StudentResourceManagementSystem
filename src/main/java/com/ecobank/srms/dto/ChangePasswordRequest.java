package com.ecobank.srms.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class ChangePasswordRequest {

    @NotEmpty(message = "jambNo cannot be empty")
    @Size(min = 4,message = "jambNo must be more than 4")
    private String jambNo;

    @Size(min = 8, message = "Current Password must be more than 8 characters")
    private String currentPassword;

    @Size(min = 8, message = "New Password must be more than 8 characters")
    private String newPassword;

    @Size(min = 8, message = "Confirm Password must be more than 8 characters")
    private String confirmPassword;
}
