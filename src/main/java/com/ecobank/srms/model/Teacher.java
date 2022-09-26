package com.ecobank.srms.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "admin_seq")
    @SequenceGenerator(name = "admin_seq", sequenceName = "admin_seq", allocationSize = 7)
    private String id_no;

    private String username;

    private String password;

    @NotNull
    @Column(name = "DATE",nullable = false)
    private Date date_Created;

    @Column(name = "Email",unique = true ,nullable = false)
    private String email;
}
