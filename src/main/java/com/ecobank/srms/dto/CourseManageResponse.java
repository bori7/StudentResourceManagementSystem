package com.ecobank.srms.dto;

import lombok.Data;

@Data
public class CourseManageResponse {

    private String resp_code;
    private String resp_msg;
    private Object data;
}
