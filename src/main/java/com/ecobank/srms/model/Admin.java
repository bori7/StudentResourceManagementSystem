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
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long adminId;

    private String username;

    @NotNull
    private String password;

    @NotNull
    @Column(name = "DATE",nullable = false)
    private Date date_Created;

    private String fName;
}
