package com.ecobank.srms.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class AdminCreateStudentResponse {
    private String level;
    private String department;
    private String jambNo;
    private String password;
    private Date date_Created;
    private Long dept_id;
    private String email;
    private String message;
}
