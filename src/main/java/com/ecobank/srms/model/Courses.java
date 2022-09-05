package com.ecobank.srms.model;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
//@Table(name =  "Course")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Courses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long courseId;

    @NotNull
    @Column(name = "Course_code",unique = false)
    private String course_code;

    @NotNull
    @Column(name = "Name_of_Course",unique = false)
    private String nameOfCourse;

    @NotNull
    @Column(name = "Course_Description",unique = false,nullable = false)
    private String course_Descr;


    @NotNull
    @Column(name = "Length_of_Course",unique = false,nullable = false)
    private String len_course;

    @NotNull
    @Column(name = "Unit_of_Course",nullable = false)
    private String unit_of_course;

    @NotNull
    @Column(name = "Status_course",nullable = false)
    private String status_course;

    @NotNull
    @Column(name = "Department_Name" ,unique = false,nullable = false)
    private String department_name;
}
