package com.ecobank.srms.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AdminFindStudentResponse {
    private String code;
    private String response;
    private List list;
    private String message;

}
