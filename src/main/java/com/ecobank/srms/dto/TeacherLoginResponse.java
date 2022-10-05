package com.ecobank.srms.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TeacherLoginResponse {
    private String message;
    private String Response;
    private String token;
    private String username;
    private Long matric;
}
