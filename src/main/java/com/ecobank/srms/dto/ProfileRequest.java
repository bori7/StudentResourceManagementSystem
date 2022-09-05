package com.ecobank.srms.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class ProfileRequest {
    private String email;
    private String userName;
    private String firstName;
    private String lastName;
    private String sex;
    private String department;
    private String faculty;
    private String dateOfBirth;
    private String picture;
    private String midName;
    private String age;
}
