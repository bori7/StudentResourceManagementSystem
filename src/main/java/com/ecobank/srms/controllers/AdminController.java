package com.ecobank.srms.controllers;

import com.ecobank.srms.Service.AdminService;
import com.ecobank.srms.Service.DepartmentService;
import com.ecobank.srms.Service.StudentService;
import com.ecobank.srms.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public ResponseEntity register(@Valid @RequestBody AdminRegisterRequest adminRegisterRequest) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(adminService.register(adminRegisterRequest));
    }

    @PostMapping(value = "/login")
    public ResponseEntity login(@Valid @RequestBody AdminLoginRequest adminLoginRequest) throws IOException {
        return ResponseEntity.ok(adminService.login(adminLoginRequest));
    }

    @PostMapping(value = "/reset_password")
    public ResponseEntity reset(@Valid @RequestBody AdminResetPasswordRequest adminResetPasswordRequest) throws IOException {
        return ResponseEntity.ok(adminService.reset(adminResetPasswordRequest));
    }

    @PostMapping(value = "/change_password")
    public ResponseEntity changePassword(@Valid @RequestBody AdminChangePasswordRequest adminChangePasswordRequest) throws IOException {
        return ResponseEntity.ok(adminService.changePassword(adminChangePasswordRequest));
    }

    @PostMapping(value = "/create_courses")
    public ResponseEntity create(@Valid @RequestBody AdminCreateCourseRequest adminCreateCourseRequest) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(adminService.create(adminCreateCourseRequest));
    }

    @PostMapping(value = "/create_department")
    public ResponseEntity createDept(@RequestBody AdminCreateDepartmentRequest adminCreateDepartmentRequestRequest) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(adminService.createDept(adminCreateDepartmentRequestRequest));
    }

    @PostMapping(value = "/create_student")
    public ResponseEntity createStud(@Valid @RequestBody AdminCreateStudentRequest adminCreateStudentRequest) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(adminService.createStud(adminCreateStudentRequest));
    }

    @GetMapping(value = "/display_dept")
    public ResponseEntity displayDept() throws IOException {
        return ResponseEntity.ok(departmentService.displayDept());
    }


    @GetMapping(value = "/display_student_dept")
    public ResponseEntity displayStudDept2(@RequestParam String deptName) throws IOException {
        return ResponseEntity.ok(adminService.displayStudDept(deptName));
    }

//    @GetMapping(value = "/display_student_dept")
//    public ResponseEntity displayStudDept3(@RequestParam String deptName) throws IOException {
//        return ResponseEntity.ok(adminService.displayStudDept(deptName));
//    }

//    @PostMapping(value = "/display_student_dept")
//    public ResponseEntity displayStudDept(@Valid @RequestBody AdminFindStudentRequest adminFindStudentRequest) throws IOException {
//        return ResponseEntity.ok(adminService.displayStudDept(adminFindStudentRequest));
//    }

    @GetMapping(value = "/count_all_students")
    public ResponseEntity displayCountStud() throws IOException{
        return ResponseEntity.ok(studentService.countStud());
    }

    @GetMapping(value = "/count_all_departments")
    public ResponseEntity displayCountDept() throws IOException{
        return ResponseEntity.ok(departmentService.countDept());
    }

//    @PostMapping(value = "/count_student_dept")
//    public ResponseEntity countStudDept1(@Valid @RequestBody AdminCountStudDeptRequest adminCountStudDeptRequest) throws IOException {
//        return ResponseEntity.ok(departmentService.displaycountStudDept(adminCountStudDeptRequest));
//    }

    @GetMapping(value = "/count_student_dept")
    public ResponseEntity countStudDept(@RequestParam String deptName) throws IOException {
        return ResponseEntity.ok(departmentService.displaycountStudDept(deptName));
    }


//    @PostMapping(value = "/display_student_level")
//    public ResponseEntity displayStudLevel1(@Valid @RequestBody AdminFindStudentLevelRequest adminFindStudentLevelRequest) throws IOException {
//        return ResponseEntity.ok(adminService.displayStudLevel(adminFindStudentLevelRequest));
//    }

    @GetMapping(value = "/display_student_level")
    public ResponseEntity displayStudLevel(@RequestParam String level) throws IOException {
        return ResponseEntity.ok(adminService.displayStudLevel(level));
    }

    @GetMapping(value = "/count_all_students_by_departments")
    public ResponseEntity displayCountStudbyDept() throws IOException{
        return ResponseEntity.ok(studentService.displayCountStudbyDept());
    }

//    @PostMapping(value = "/show_count_all_level_by_department")
//    public ResponseEntity displayStudLevel(@Valid @RequestBody AdminFindStudentRequest adminFindStudentRequest) throws IOException {
//        return ResponseEntity.ok(studentService.showCountLevelByDepartment(adminFindStudentRequest));
//    }

    @GetMapping(value = "/show_count_all_level_by_department")
    public ResponseEntity countStudLevelbydepartment(@RequestParam String deptName) throws IOException {
        return ResponseEntity.ok(studentService.showCountLevelByDepartment(deptName));
    }

    @GetMapping(value = "/show_number_all_old_students")
    public ResponseEntity showNumberOldStudents() throws IOException{
        return ResponseEntity.ok(studentService.ShowCountOldStudents());
    }

    @GetMapping(value = "/show_number_all_new_students")
    public ResponseEntity showNumberNewStudents() throws IOException{
        return ResponseEntity.ok(studentService.ShowCountNewStudents());
    }

}
