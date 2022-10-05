package com.ecobank.srms.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AdminCountStudDeptResponse {
    private String response;
    private String message;
    private Long count;
}
