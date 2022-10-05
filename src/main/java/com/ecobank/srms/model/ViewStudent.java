package com.ecobank.srms.model;

import lombok.Data;

import java.util.Date;


@Data
public class ViewStudent {
    private String jambNo;
    private String email;
    private String level;
    private String department;
    private Date date_Created;

}
