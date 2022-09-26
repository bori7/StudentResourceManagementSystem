package com.ecobank.srms.dto;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

@Data
public class AdminCreateStudentRequest {
    private String level;
    private String department;
    private String jambNo;
    private String password;
    private Date date_Created;
    private Long dept_id;
    private String email;

}
