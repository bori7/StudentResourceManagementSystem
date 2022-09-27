package com.ecobank.srms.model;


import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CourseManage {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseManageId;

    @NotNull
    @Column(name = "student_Reg",nullable = false)
    private String studReg;

    @NotNull
    @Column(name = "course_Id",nullable = false)
    private Long courseId;

    @NotNull
    @Column(name = "course_Name",nullable = false)
    private String course_Name;


    // @NotNull
    //@Column(name = "department_name",unique = true,nullable = false)
    //private Long dept_name;
}
