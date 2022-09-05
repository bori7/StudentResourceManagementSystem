package com.ecobank.srms.controllers;

import com.ecobank.srms.Service.StudentService;
import com.ecobank.srms.dto.ChangePasswordRequest;
import com.ecobank.srms.dto.LoginRequest;
import com.ecobank.srms.dto.ResetPasswordRequest;
import com.ecobank.srms.dto.StudentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("api/v1/student")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @PostMapping(value="/register")
    public ResponseEntity register(@RequestBody StudentRequest studentRequest) throws IOException {
        //return ResponseEntity.ok().body(studentService.Register(studentRequest));
        return ResponseEntity.ok(studentService.Register(studentRequest));
    }
    @PostMapping(value="/login")
    public ResponseEntity Login(@RequestBody LoginRequest loginRequest) throws IOException {
        //return ResponseEntity.ok().body(studentService.Register(studentRequest));
        return ResponseEntity.ok(studentService.Login(loginRequest));
    }

    @PostMapping(value="/change_password")
    public ResponseEntity updateCurrentPassword(@RequestBody ChangePasswordRequest changePasswordRequest) throws IOException {
        //return ResponseEntity.ok().body(studentService.Register(studentRequest));
        return ResponseEntity.ok(studentService.updateCurrentPassword(changePasswordRequest));
    }

    @PostMapping(value="/reset_password")
    public ResponseEntity resetCurrentPassword(@RequestBody ResetPasswordRequest resetPasswordRequest) throws IOException {
        //return ResponseEntity.ok().body(studentService.Register(studentRequest));
        return ResponseEntity.ok(studentService.reset(resetPasswordRequest));
    }


}
