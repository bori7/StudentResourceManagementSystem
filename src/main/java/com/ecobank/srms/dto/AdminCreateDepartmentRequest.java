package com.ecobank.srms.dto;


import lombok.Data;

import javax.validation.constraints.NotNull;


@Data
public class AdminCreateDepartmentRequest {
    @NotNull(message = "Department Name cannot be empty")
    private String deptName;
}
