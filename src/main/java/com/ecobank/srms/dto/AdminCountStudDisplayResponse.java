package com.ecobank.srms.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AdminCountStudDisplayResponse {
    private String message;
    private Long count;
    private String response;
}
