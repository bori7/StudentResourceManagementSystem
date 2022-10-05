package com.ecobank.srms.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TeacherRegisterResponse {
    private String message;
    private String email;
    private String username;
}
