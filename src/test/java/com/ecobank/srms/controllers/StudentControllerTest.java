package com.ecobank.srms.controllers;

import com.ecobank.srms.Service.StudentService;
import com.ecobank.srms.Service.StudentServiceImpl;
import com.ecobank.srms.dto.*;
import com.ecobank.srms.utils.Response;
import com.ecobank.srms.utils.Token;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.testng.Assert;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StudentControllerTest {

    private String globaltoken ="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJxZGZzeXJ0aXlqdHlmZHJydHlmaHI1dWk3eXRqaCIsImlzcyI6IkFueXRoaW5nIiwiZXhwIjoxNjY4Nzc5MzcyfQ.MiNM0Ks3lejJjBt8AbhUzVCZQHKdf21_uTMOQrEkuX9m_hnCEjk5j9XNevo6imU1bZRwd59EAkbrIynnfQWpuA";

    @Autowired
    private StudentService studentService;


    @Autowired
    private StudentServiceImpl studentServiceimpl;

    @Autowired
    HttpServletRequest httpServletRequest;


    @LocalServerPort
    private int port;

//    @Value("${client.id}")
//    private String id;
//
//    @Value("${client.sourcecode}")
//    private String sourcecode;
//
//    @Value("${client.secret}")
//    private String secret;




    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    Token token;
    String accessToken;






    @Test
    void register() throws IOException {
        Date date = new Date();
        StudentRequest studentRequest = new StudentRequest();
        studentRequest.setJambNo("JambTest1034");
        studentRequest.setEmail("randomemail1034@gmail.com");
        studentRequest.setPassword("JambTest1034");
        studentRequest.setDepartment("Computer Science");
        studentRequest.setDate_Created(date);
        studentRequest.setLevel("300");
        studentRequest.setConfirmPassword("JambTest1034");
        studentRequest.setDept_id(2L);


        HttpEntity<StudentRequest>entity = new HttpEntity(studentRequest, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/v1/student/register"), HttpMethod.POST, entity ,String.class);



        String actual = response.getBody();



        assertTrue(actual.contains("This registration exists"));

        Assert.assertEquals(response.getStatusCodeValue(),400);


    }

    @Test
    void login() throws IOException {


        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setJambNo("JambTest1034");
        loginRequest.setPassword("JambTest1034");



        HttpEntity entity = new HttpEntity(loginRequest, headers);



        ResponseEntity  response = restTemplate.exchange(
                createURLWithPort("/api/v1/student/login"), HttpMethod.POST, entity , Object.class);


        System.out.println(response.getBody());
        System.out.println("Class: "+response.getBody().getClass());
        if (response.getBody() instanceof LinkedHashMap){
            LinkedHashMap  studentResponse = (LinkedHashMap) response.getBody();
            System.out.println("studentResponse: "+studentResponse);
            accessToken = (String) studentResponse.get("token");
            this.globaltoken = accessToken;
            System.out.println(accessToken);
        }
        if (response.getBody() instanceof StudentResponse){
            StudentResponse  studentResponse = (StudentResponse) response.getBody();
            System.out.println("studentResponse: "+studentResponse);

        }



        String actual =  response.getBody().toString();
        StringBuilder activator = new StringBuilder(response.getBody().toString());


        Object obj = response.getBody();




        assertTrue(actual.contains("Login Successful"));
        assertTrue(actual.contains(loginRequest.getJambNo()));

    }

    @Test
    void updateCurrentPassword() throws IOException {
         if (globaltoken == null){
             System.out.println("I am null");
         }

        headers.setBearerAuth(globaltoken);




        ChangePasswordRequest changePasswordRequest = new ChangePasswordRequest("JambTest1034","JambTest1034_new","JambTest1034","JambTest1034");

        HttpEntity entity = new HttpEntity(changePasswordRequest, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/v1/student/change_password"), HttpMethod.POST, entity ,String.class);

        System.out.println(response.getStatusCode());


        Object obj = response.getBody();
        System.out.println(obj);

        String actual = response.getBody();
        assertTrue(actual.contains("Password successfully changed"));

    }

    @Test
    void resetCurrentPassword() {
        ResetPasswordRequest resetPasswordRequest = new ResetPasswordRequest("JambTest1034","JambTest1034","JambTest1034");


        headers.setBearerAuth(globaltoken);
        System.out.println(globaltoken);

        HttpEntity entity = new HttpEntity(resetPasswordRequest, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/v1/student/reset_password"), HttpMethod.POST, entity ,String.class);


        String actual = response.getBody();
        System.out.println(actual);
        assertTrue(actual.contains("Password successfully Reset"));
    }
}