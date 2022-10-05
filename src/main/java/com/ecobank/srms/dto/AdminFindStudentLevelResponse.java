package com.ecobank.srms.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdminFindStudentLevelResponse {
    private String response;
    private String message;
    private Object data;
}
