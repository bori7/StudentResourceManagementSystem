package com.ecobank.srms.dto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResetPasswordRequest {
    private String JambNo;
    private String newPassword;
    private String confirmPassword;
}
