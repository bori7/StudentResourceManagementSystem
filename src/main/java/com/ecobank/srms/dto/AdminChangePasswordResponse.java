package com.ecobank.srms.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdminChangePasswordResponse {
    private String username;
    private String message;
    private String code;
}
