package com.ecobank.srms.Service;

import com.ecobank.srms.SrmsApplication;
import com.ecobank.srms.dto.TeacherLoginRequest;
import com.ecobank.srms.dto.TeacherLoginResponse;
import com.ecobank.srms.dto.TeacherRegisterRequest;
import com.ecobank.srms.dto.TeacherRegisterResponse;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SrmsApplication.class)
@ComponentScan(basePackages = "com.ecobank.srms")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TeacherServiceImplTest {
    @Autowired
    TeacherService teacherService;

    @Test
    void register() {
        Date date = new Date();
        TeacherRegisterRequest teacherRegisterRequest = new TeacherRegisterRequest();
        teacherRegisterRequest.setUsername("TestingTeacherUser");
        teacherRegisterRequest.setPassword("TestingTeacherUserpassword");
        teacherRegisterRequest.setConfirmPassword("TestingTeacherUserpassword");
        teacherRegisterRequest.setDate_Created(date);
        teacherRegisterRequest.setEmail("TestingTeacher@gmail.com");
        TeacherRegisterResponse teacherRegisterResponse_actual = teacherService.register(teacherRegisterRequest);

        TeacherRegisterResponse teacherRegisterResponse_expected = TeacherRegisterResponse
                .builder()
                .message("The Username : " + teacherRegisterRequest.getUsername() + " Already Exists")
                .username(null)
                .email(null)
                .build();

        Assert.assertEquals(teacherRegisterResponse_expected,teacherRegisterResponse_actual);
    }

    @Test
    void login() {
        TeacherLoginRequest teacherLoginRequestRequest = new TeacherLoginRequest();
        teacherLoginRequestRequest.setUsername("TestingTeacherUser");
        teacherLoginRequestRequest.setPassword("TestingTeacherUserpassword");

        TeacherLoginResponse teacherLoginResponse_expected = teacherService.login(teacherLoginRequestRequest);

        TeacherLoginResponse teacherLoginResponse_actual = TeacherLoginResponse
                .builder()
                .token(teacherLoginResponse_expected.getToken())
                .Response("00")
                .matric(72L)
                .username("TestingTeacherUser")
                .message("Login Successful")
                .build();

        Assert.assertEquals(teacherLoginResponse_actual,teacherLoginResponse_expected);
    }
}