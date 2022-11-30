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
public class IdVerification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long VerifyId;

    @NotNull
    private String userId;

//    @NotNull
//    @Column(name = "DATE",nullable = false)
//    private Date date_Created;

}
