package com.ecobank.srms.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AdminCountDeptDisplayResponse {
    private String message;
    private String response;
    private Long count;
}
