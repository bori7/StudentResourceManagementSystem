package com.ecobank.srms.controllers;

import com.ecobank.srms.Service.StudentService;
import com.ecobank.srms.dto.ChangePasswordRequest;
import com.ecobank.srms.dto.LoginRequest;
import com.ecobank.srms.dto.ResetPasswordRequest;
import com.ecobank.srms.dto.StudentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//import javax.validation.Valid;
import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("api/v1/student")
@CrossOrigin
public class StudentController {
    @Autowired
    private StudentService studentService;
    @PostMapping(value="/register")
    public ResponseEntity register( @Valid @RequestBody  StudentRequest studentRequest) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.Register(studentRequest));
//        return ResponseEntity.created.(studentService.Register(studentRequest));
    }
    @PostMapping(value="/login")
    public ResponseEntity Login( @Valid @RequestBody LoginRequest loginRequest) throws IOException {
        //return ResponseEntity.ok().body(studentService.Register(studentRequest));
        return ResponseEntity.ok(studentService.Login(loginRequest));
    }

    @PostMapping(value="/change_password")
    public ResponseEntity updateCurrentPassword(@Valid @RequestBody ChangePasswordRequest changePasswordRequest) throws IOException {
        //return ResponseEntity.ok().body(studentService.Register(studentRequest));
        return ResponseEntity.ok(studentService.updateCurrentPassword(changePasswordRequest));
    }

    @PostMapping(value="/reset_password")
    public ResponseEntity resetCurrentPassword( @Valid @RequestBody ResetPasswordRequest resetPasswordRequest) throws IOException {
        //return ResponseEntity.ok().body(studentService.Register(studentRequest));
        return ResponseEntity.ok(studentService.reset(resetPasswordRequest));
    }


}
