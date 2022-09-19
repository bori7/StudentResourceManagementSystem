package com.ecobank.srms.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileResponse {
    private String email;
    //private String userName;
    private String fName;
    private String surName;
    private String sex;
    private String department;
    private String faculty;
    private String dateOfBirth;
    private String picture;
    private String midName;
    private String age;
    private String lga;
    private String stOfOrg;
    private String message;

    //private String dobText;
}
