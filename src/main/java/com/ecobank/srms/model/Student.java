package com.ecobank.srms.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
//@Table(name =  "Student")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long studentId;


//
//    @NotNull
//    //@Column(name = "reg_no",unique = true,nullable = false)
//    @OneToOne(fetch=FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "student")
//    private BioMedData bioMedData;


//    @NotNull
//    @Column(name = "reg_no",unique = true,nullable = false)
//    //@OneToOne(fetch=FetchType.LAZY,cascade = CascadeType.ALL)
//    private String regNo;


    @NotNull(message = "level is empty")
    @NotBlank(message = "level is mandatory")
    @Column(name = "LEVEL",unique = false,nullable = false)
    private String level;

    @NotNull
    @Column(name = "DEPT",unique = false,nullable = false)
    private String department;


//    @NotNull
//    @Column(name = "L_NAME",unique = false,nullable = false)
//    private String lastName;
//
//    @NotNull
//    @Column(name = "F_NAME",unique = false,nullable = false)
//    private String firstName;

    @NotNull
    @Column(name = "JAMBNO",unique = true,nullable = false)
    private String jambNo;

    @NotNull
    @Column(name = "PASSWORD",nullable = false)
    private String password;

    @NotNull
    @Column(name = "DATE",nullable = false)
    @Temporal(value = TemporalType.DATE)
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "YYYY-MM-DD")
    private Date date_Created;

//    @Column(name = "MATRIC_NO", unique = true, nullable = false)
//    private String mat_no;

    //
    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "dept_id")

    @NotNull
    @Column(name = "dept_id",nullable = false)
    private Long dept_id;

    @Column(name = "Email",unique = true ,nullable = false)
    private String email;
    //private Department stud_dept;



}