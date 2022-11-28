package com.ecobank.srms.controllers;

import com.ecobank.srms.Service.AdminService;
import com.ecobank.srms.dto.*;
import com.ecobank.srms.utils.Token;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TeacherControllerTest {

    private String globaltoken ="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJxZGZzeXJ0aXlqdHlmZHJydHlmaHI1dWk3eXRqaCIsImlzcyI6IkFueXRoaW5nIiwiZXhwIjoxNjY5MTIzMjYxfQ.fc_tND9STtAPSwwmpuIVpOS8NcA1raus5CUeonvgXtL_aDXvwa6hEDhi9OIzCuxNmXlnMoSE9qjkfx8AoOroAQ";


    @Autowired
    HttpServletRequest httpServletRequest;

    @Autowired
    AdminService adminService;


    @LocalServerPort
    private int port;

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    Token token;
    String accessToken;

    @Test
    void register() {
        Date date = new Date();
        TeacherRegisterRequest teacherRegisterRequest = new TeacherRegisterRequest();
        teacherRegisterRequest.setUsername("TestingTeacherUser");
        teacherRegisterRequest.setPassword("TestingTeacherUserpassword");
        teacherRegisterRequest.setConfirmPassword("TestingTeacherUserpassword");
        teacherRegisterRequest.setDate_Created(date);
        teacherRegisterRequest.setEmail("TestingTeacher@gmail.com");
        String teacherExpected = "{\"message\":\"The Username : TestingTeacherUser Already Exists\",\"email\":null,\"username\":null}";



        HttpEntity<StudentRequest> entity = new HttpEntity(teacherRegisterRequest, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/v1/teacher/register"), HttpMethod.POST, entity ,String.class);


        System.out.println(response.getStatusCode());

        String teacherActual = response.getBody();
        System.out.println(teacherActual);


        assertTrue(teacherActual.contains(teacherExpected));

//        Assert.assertEquals(teacherActual,teacherExpected);
    }


    @Test
    void login() {
        TeacherLoginRequest teacherLoginRequest = new TeacherLoginRequest();
        String string = new String();

        teacherLoginRequest.setUsername("TestingTeacherUser");
        teacherLoginRequest.setPassword("TestingTeacherUserpassword");

        HttpEntity<StudentRequest> entity = new HttpEntity(teacherLoginRequest, headers);

        ResponseEntity response = restTemplate.exchange(
                createURLWithPort("/api/v1/teacher/login"), HttpMethod.POST, entity ,Object.class);

        if (response.getBody() instanceof LinkedHashMap){
            LinkedHashMap  studentResponse = (LinkedHashMap) response.getBody();
            System.out.println("studentResponse: "+studentResponse);
            accessToken = (String) studentResponse.get("token");
            this.globaltoken = accessToken;
            System.out.println(accessToken);
        }


        String actual = Objects.requireNonNull(response.getBody()).toString();

        assertTrue(actual.contains("Login Successful"));
    }
}