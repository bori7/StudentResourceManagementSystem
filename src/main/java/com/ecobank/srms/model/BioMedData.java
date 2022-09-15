package com.ecobank.srms.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BioMedData{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long studentId;

    @NotNull
    @Column(name = "First_Name",unique = false,nullable = false)
    private String fName;

    @NotNull
    @Column(name = "Surname_Name",unique = false,nullable = false)
    private String surName;

    @NotNull
    @Column(name = "Middle_Name",unique = false,nullable = false)
    private String midName;

    @NotNull
    @Column(name = "JAMB_REG_NO",unique = true,nullable = false)
    private String jambNo;

    //Add Reg_NO

    //@NotNull
    //@Column(name = "reg_no",unique = true,nullable = false)
//    @OneToOne(fetch=FetchType.LAZY,cascade = CascadeType.ALL)
//    @JoinColumn(name = "stud_reg2")
//    private Student student;
//

//    @Column(name = "reg_no",unique = true,nullable = false)
//    private String reg_no;

    @NotNull
    @Column(name = "Date_of_Birth",unique = false,nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private String dateOfBirth;

    @NotNull
    @Column(name = "Age",unique = false,nullable = false)
    private String age;


    @NotNull
    @Column(name = "Sex",unique = false,nullable = false)
    private String sex;

    @NotNull
    @Column(name = "Marital_Status",unique = false,nullable = false)
    private String mStatus;

    @NotNull
    @Column(name = "DEPT",unique = false,nullable = false)
    private String department;


    @NotNull
    @Column(name = "Faculty",unique = false,nullable = false)
    private String faculty;

    @NotNull
    @Column(name = "Address",unique = false,nullable = false)
    private String address;

    @NotNull

    @Column(name = "Email",unique = false,nullable = false)
    private String email;

    @NotNull
    @Column(name = "Phone_NO",unique = false,nullable = false)
    private String phoneNo;

    @NotNull

    @Column(name = "Nationality",nullable = false)
    private String nationality;

    @NotNull

    @Column(name = "Religion",nullable = false)
    private String religion;

    @NotNull

    @Column(name = "State_of_origin",nullable = false)
    private String stOfOrg;

    @NotNull

    @Column(name = "LGA",nullable = false)
    private String lga;

    @NotNull

    @Column(name = "Parents_Guardians_Name",nullable = false)
    private String parName;

    @NotNull

    @Column(name = "Occupation_of_parents_guardians",nullable = false)
    private String occName;
    @NotNull

    @Column(name = "Address_of_parents_guardians",nullable = false)
    private String parAdd;

    @NotNull
    @Column(name = "Email_of_parents_guardians",nullable = false)
    private String parEmail;

    @NotNull
    @Column(name = "Phone_NO_of_parents_guardians",nullable = false)
    private String parNO;

    private String picture;


//   Add Reg_NO
//    @NotNull
//    @Column(name = "reg_no",unique = true,nullable = false)
//    private String regNo;

//    @NotNull
//    @Column(name = "Picture_of_student",nullable = false)
//    @Lob
//    @Basic(fetch = FetchType.LAZY)
//    private byte[] profilePicture;
}
