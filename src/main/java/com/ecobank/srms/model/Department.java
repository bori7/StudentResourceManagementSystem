package com.ecobank.srms.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Department_ID" ,unique = true,nullable = false)
    private Long Dept_id;


    @NotNull
    @Column(name = "department_name" ,unique = true,nullable = false)
    private String deptName;

}
