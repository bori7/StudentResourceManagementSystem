package com.ecobank.srms.controllers;

import com.ecobank.srms.Service.AdminService;
import com.ecobank.srms.Service.DepartmentService;
import com.ecobank.srms.Service.StudentService;
import com.ecobank.srms.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@CrossOrigin
@RequestMapping("api/v1/admin")
public class AdminController {


    @Autowired
    private AdminService adminService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private StudentService studentService;

    @PostMapping(value = "/register")
    public ResponseEntity register(@RequestBody AdminRegisterRequest adminRegisterRequest) throws IOException {
        return ResponseEntity.ok(adminService.register(adminRegisterRequest));
    }

    @PostMapping(value = "/login")
    public ResponseEntity login(@RequestBody AdminLoginRequest adminLoginRequest) throws IOException {
        return ResponseEntity.ok(adminService.login(adminLoginRequest));
    }

    @PostMapping(value = "/reset_password")
    public ResponseEntity reset(@RequestBody AdminResetPasswordRequest adminResetPasswordRequest) throws IOException {
        return ResponseEntity.ok(adminService.reset(adminResetPasswordRequest));
    }

    @PostMapping(value = "/create_courses")
    public ResponseEntity create(@RequestBody AdminCreateCourseRequest adminCreateCourseRequest) throws IOException {
        return ResponseEntity.ok(adminService.create(adminCreateCourseRequest));
    }

    @PostMapping(value = "/create_department")
    public ResponseEntity createDept(@RequestBody AdminCreateDepartmentRequest adminCreateDepartmentRequestRequest) throws IOException {
        return ResponseEntity.ok(adminService.createDept(adminCreateDepartmentRequestRequest));
    }

    @PostMapping(value = "/create_student")
    public ResponseEntity createStud(@RequestBody AdminCreateStudentRequest adminCreateStudentRequest) throws IOException {
        return ResponseEntity.ok(adminService.createStud(adminCreateStudentRequest));
    }

    @GetMapping(value = "/display_dept")
    public ResponseEntity displayDept() throws IOException {
        return ResponseEntity.ok(departmentService.displayDept());
    }

    @GetMapping(value = "/display_student")
    public ResponseEntity displayStud() throws IOException {
        return ResponseEntity.ok(studentService.displayStud());
    }

    @PostMapping(value = "/display_student_dept")
    public ResponseEntity displayStudDept(@RequestBody AdminFindStudentRequest adminFindStudentRequest) throws IOException {
        return ResponseEntity.ok(adminService.displayStudDept(adminFindStudentRequest));
    }
}
