package com.ecobank.srms.dto;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AdminCreateDepartmentResponse {
    private String deptName;
    private String message;
}
