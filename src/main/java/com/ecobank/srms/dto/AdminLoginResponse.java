package com.ecobank.srms.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdminLoginResponse {
    private String message;
    private String token;
    private String username;
}
