package com.ecobank.srms.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChangePasswordRequest {
    private String jambNo;
    private String currentPassword;
    private String newPassword;
    private String confirmPassword;
}
