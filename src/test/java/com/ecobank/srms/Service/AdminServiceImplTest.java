package com.ecobank.srms.Service;

import com.ecobank.srms.SrmsApplication;
import com.ecobank.srms.dto.*;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SrmsApplication.class)
@ComponentScan(basePackages = "com.ecobank.srms")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AdminServiceImplTest {
    @Autowired
    AdminService adminService;

    @Test
    void register() throws IOException {
        Date date = new Date();
        AdminRegisterRequest adminRegisterRequest = new AdminRegisterRequest();
        adminRegisterRequest.setUsername("TestAdminUser");
        adminRegisterRequest.setPassword("TestAdminUserpassword");
        adminRegisterRequest.setDate_Created(date);
        adminRegisterRequest.setFName("TestAdminFname");

        AdminRegisterResponse adminRegisterResponse_actual = adminService.register(adminRegisterRequest);

        AdminRegisterResponse adminRegisterResponse_expected = AdminRegisterResponse
                .builder()
                .fName(null)
                .message("This Username Already Exists")
                .username(null)
                .build();

        Assert.assertEquals(adminRegisterResponse_actual,adminRegisterResponse_expected);
    }

    @Test
    void login() throws IOException {
        AdminLoginRequest adminLoginRequest = new AdminLoginRequest();
        adminLoginRequest.setUsername("Admin101");
        adminLoginRequest.setPassword("Adminpassword");

        AdminLoginResponse adminLoginResponse_actual = adminService.login(adminLoginRequest);

        AdminLoginResponse adminLoginResponse_expected = AdminLoginResponse
                .builder()
                .username("Admin101")
                .token(adminLoginResponse_actual.getToken())
                .message("Login Successful")
                .build();

        Assert.assertEquals(adminLoginResponse_actual,adminLoginResponse_expected);

    }

    @Test
    void reset() throws IOException {
        AdminResetPasswordRequest adminResetPasswordRequest = new AdminResetPasswordRequest();
        adminResetPasswordRequest.setUsername("TestAdminUser");
        adminResetPasswordRequest.setNewPassword("TestAdminUserpassword");
        adminResetPasswordRequest.setConfirmPassword("TestAdminUserpassword");

        ResetPasswordResponse resetPasswordResponse_actual = adminService.reset(adminResetPasswordRequest);

        ResetPasswordResponse resetPasswordResponse_expected = ResetPasswordResponse
                .builder()
                .message("Password successfully Reset")
                .build();

        Assert.assertEquals(resetPasswordResponse_expected,resetPasswordResponse_actual);
    }

    @Test
    void create() throws IOException {
        AdminCreateCourseRequest adminCreateCourseRequest = new AdminCreateCourseRequest();
        adminCreateCourseRequest.setDepartmentname("Computer Science");
        adminCreateCourseRequest.setCourse_code("CSC9001");
        adminCreateCourseRequest.setCourse_Descr("Testing of Integration Testing through Service");
        adminCreateCourseRequest.setLen_course("Double SEMESTER");
        adminCreateCourseRequest.setNameOfCourse("Test of AdminService Course");
        adminCreateCourseRequest.setUnit_of_course("3");
        adminCreateCourseRequest.setStatus_course("R");

         AdminCreateCourseResponse adminCreateCourseResponse_actual = adminService.create(adminCreateCourseRequest);

         AdminCreateCourseResponse adminCreateCourseResponse_expected = AdminCreateCourseResponse
                 .builder()
                 .unit_of_course("3")
                 .nameOfCourse("Test of AdminService Course")
                 .departmentname("COMPUTER SCIENCE")
                 .course_code("CSC9001")
                 .course_Descr("Testing of Integration Testing through Service")
                 .status_course("R")
                 .len_course("Double SEMESTER")
                 .message("Course has been created")
                 .build();

         Assert.assertEquals(adminCreateCourseResponse_actual,adminCreateCourseResponse_expected);
    }

    @Test
    void createDept() {
        AdminCreateDepartmentRequest adminCreateDepartmentRequest = new AdminCreateDepartmentRequest();
        adminCreateDepartmentRequest.setDeptName("ADMIN SERVICE TESTING DEPARTMENT");

        AdminCreateDepartmentResponse adminCreateDepartmentResponse_actual = adminService.createDept(adminCreateDepartmentRequest);

        AdminCreateDepartmentResponse adminCreateDepartmentResponse_expected = AdminCreateDepartmentResponse
                .builder()
                .deptName("ADMIN SERVICE TESTING DEPARTMENT")
                .message("Department has been created")
                .build();

Assert.assertEquals(adminCreateDepartmentResponse_actual,adminCreateDepartmentResponse_expected);
    }

    @Test
    void createStud() {
        Date date = new Date();
        AdminCreateStudentRequest adminCreateStudentRequest = new AdminCreateStudentRequest();
        adminCreateStudentRequest.setJambNo("AdminTestcreateStudent01");
        adminCreateStudentRequest.setDept_id(2L);
        adminCreateStudentRequest.setPassword("AdminTestcreateStudent01password");
        adminCreateStudentRequest.setLevel("500");
        adminCreateStudentRequest.setDepartment("Computer Science");
        adminCreateStudentRequest.setDate_Created(date);
        adminCreateStudentRequest.setEmail("AdminTestcreateStudent01@gmail.com");

        AdminCreateStudentResponse adminCreateStudentResponse_actual = adminService.createStud(adminCreateStudentRequest);

        AdminCreateStudentResponse adminCreateStudentResponse_expected = AdminCreateStudentResponse
                .builder()
                .date_Created(adminCreateStudentResponse_actual.getDate_Created())
                .level("500")
                .jambNo("AdminTestcreateStudent01")
                .email("AdminTestcreateStudent01@gmail.com")
                .dept_id(2L)
                .message("New Student Created")
                .department("COMPUTER SCIENCE")
                .build();

        Assert.assertEquals(adminCreateStudentResponse_expected,adminCreateStudentResponse_actual);
    }

    @Test
    void displayStudDept() {
        AdminFindStudentRequest adminFindStudentRequest = new AdminFindStudentRequest();
        adminFindStudentRequest.setDeptName("Computer Science");
        Object deptviewExpected = adminService.displayStudDept(adminFindStudentRequest);

        List<Object> studViewactual = new ArrayList<>(Arrays.asList(
                "Student(studentId=4, level=400, department=Computer Science, jambNo=400, password=$2a$10$H/yyfFKDIj9a/BgiqxQ5Q.qO7YKgzYaKEHSsv4vpGzAEOHrvo6wtC, date_Created=2022-10-13, dept_id=2, email=Student4.com), Student(studentId=5, level=400, department=Computer Science, jambNo=600, password=$2a$10$WnPvqry1DTLxR4RgW6xvgueGFXxWZpuPPRGoI3vBoNncccA4676gC, date_Created=2022-10-13, dept_id=2, email=Student6.com), Student(studentId=6, level=400, department=Computer Science, jambNo=19901990, password=$2a$10$P.acK3XlD.mjpfjkhOwHgu00I5KPAWjbszxETDgoepyaSBitGGrm6, date_Created=2022-10-14, dept_id=2, email=Student1990.com), Student(studentId=7, level=400, department=Computer Science, jambNo=90239023, password=$2a$10$5tIkTLj74Khdfq0fdZ9wveBKeGD7aZcFXoIJAcQvvLxrQm1voDJQK, date_Created=2022-10-17, dept_id=2, email=Student90239023.com), Student(studentId=8, level=400, department=Computer Science, jambNo=20099002, password=$2a$10$DP/pTPWjUdVCNIQLR/EfC.ryPORf8BHwQ2w.dELoYWB6HUZ95Ydp2, date_Created=2022-10-17, dept_id=2, email=Student20099002.com), Student(studentId=9, level=400, department=Computer Science, jambNo=18901890, password=$2a$10$Ug.RT5UMteQ5A/EiuQ6eZO6/OraiSQXrOn9gMsVRFKAaDSyop/ZN., date_Created=2022-10-17, dept_id=2, email=Student189018902.com), Student(studentId=10, level=500, department=Computer Science, jambNo=16781678, password=$2a$10$Mcgxuo7XnXqrVyKFCZWZKurLpjUJR8YMm1JsrMqAgrq6X9B5d81Zq, date_Created=2022-10-18, dept_id=2, email=Student16781678.com), Student(studentId=11, level=500, department=Computer Science, jambNo=89018901, password=$2a$10$HSJe.xSS/gNWs5w5h6ERBe4WkRRAUBgnD7AkLrk/1rPi/CHbn4bdq, date_Created=2022-10-18, dept_id=2, email=Student89018901.com), Student(studentId=12, level=500, department=Computer Science, jambNo=12344321, password=$2a$10$ck.cCodIj1uGwfTX7jkNqe6gqBo7VrvBT9tpqFT2nC0G6KVeJIIxS, date_Created=2022-10-18, dept_id=2, email=Student12344321.com), Student(studentId=14, level=500, department=Computer Science, jambNo=30903090, password=$2a$10$Vq4bXr6hfY8jNdFY7mti1u1qCh4xv8pRmxyHYO1r9sPpuslF4c8Qa, date_Created=2022-10-18, dept_id=2, email=Student30903090.com), Student(studentId=15, level=500, department=Computer Science, jambNo=30803080, password=$2a$10$IR49xSZXk3bDkaMWybzZqedh/z78HkfFlYsyqJej20HhdwdrpxFNW, date_Created=2022-10-18, dept_id=2, email=Student30803080.com), Student(studentId=144, level=300, department=Computer Science, jambNo=JambTest1001, password=$2a$10$Ulz4ZpHeKjT2Boa9e9zvlu8nZ7N2r1ybpFmwfiAEtxN492HLgUMEe, date_Created=2022-11-17, dept_id=2, email=randomemail1001@gmail.com), Student(studentId=146, level=500, department=Computer Science, jambNo=TestcreateStudent01, password=$2a$10$BWaBLvJ8CSZr4D94oFamwenGxoeUvlfLciq3GpOfqrzUcCwJBQ/.a, date_Created=2022-11-19, dept_id=2, email=TestcreateStudent01@gmail.com), Student(studentId=145, level=300, department=Computer Science, jambNo=JambTest1034, password=$2a$10$tUvxwIRevNl8wp1ov4tuseN5kkvXQRYGxGv5M/llD4fGyuv8SOyVm, date_Created=2022-11-17, dept_id=2, email=randomemail1034@gmail.com)"
        ));

       Assert.assertEquals(deptviewExpected.toString().trim(),studViewactual.toString().trim());

    }

    @Test
    void displayStudLevel() {
        AdminFindStudentLevelRequest adminFindStudentLevelRequest = new AdminFindStudentLevelRequest();
        adminFindStudentLevelRequest.setLevel("100");
        AdminFindStudentLevelResponse adminFindStudentLevelResponse_actual = adminService.displayStudLevel(adminFindStudentLevelRequest);
        AdminFindStudentLevelResponse adminFindStudentLevelResponse_expected = AdminFindStudentLevelResponse
                .builder()
                .message("Successful")
                .response("There are the students with Level :" + adminFindStudentLevelRequest.getLevel())
                .data("[ViewStudent(jambNo=20302030122213123, email=olamiolaaaa2221123@gmail.com, level=100, department=history, date_Created=2022-09-21)]")
                .build();
Assert.assertEquals(adminFindStudentLevelResponse_expected.toString().trim(),adminFindStudentLevelResponse_actual.toString().trim());
    }

    @Test
    void changePassword()
    {
        AdminChangePasswordRequest adminChangePasswordRequest = new AdminChangePasswordRequest();
        adminChangePasswordRequest.setUsername("TestAdminUser");
        adminChangePasswordRequest.setNewPassword("TestAdminUserpassword_new");
        adminChangePasswordRequest.setCurrentPassword("TestAdminUserpassword");
        adminChangePasswordRequest.setConfirmPassword("TestAdminUserpassword_new");
        AdminChangePasswordResponse adminChangePasswordResponse_expected = adminService.changePassword(adminChangePasswordRequest);

        AdminChangePasswordResponse adminChangePasswordResponse_actual = AdminChangePasswordResponse
                .builder()
                .code("00")
                .message("Password successfully changed")
                .username("TestAdminUser")
                .build();

        Assert.assertEquals(adminChangePasswordResponse_actual,adminChangePasswordResponse_expected);
    }
}