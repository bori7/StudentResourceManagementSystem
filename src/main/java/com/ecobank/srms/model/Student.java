package com.ecobank.srms.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
//@Table(name =  "Student")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
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


    @NotNull
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