package com.ecobank.srms.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AdminStudentGeneralResponse {
    private String response;
    private String code;
    private String message;
    private Long count;
}
