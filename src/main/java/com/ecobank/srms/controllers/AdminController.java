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

    @GetMapping(value = "/count_all_students")
    public ResponseEntity displayCountStud() throws IOException{
        return ResponseEntity.ok(studentService.countStud());
    }

    @GetMapping(value = "/count_all_departments")
    public ResponseEntity displayCountDept() throws IOException{
        return ResponseEntity.ok(departmentService.countDept());
    }

    @PostMapping(value = "/count_student_dept")
    public ResponseEntity countStudDept(@RequestBody AdminCountStudDeptRequest adminCountStudDeptRequest) throws IOException {
        return ResponseEntity.ok(departmentService.displaycountStudDept(adminCountStudDeptRequest));
    }


    @PostMapping(value = "/display_student_level")
    public ResponseEntity displayStudLevel(@RequestBody AdminFindStudentLevelRequest adminFindStudentLevelRequest) throws IOException {
        return ResponseEntity.ok(adminService.displayStudLevel(adminFindStudentLevelRequest));
    }

}
