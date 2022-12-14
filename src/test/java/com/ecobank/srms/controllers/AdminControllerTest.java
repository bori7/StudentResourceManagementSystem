package com.ecobank.srms.controllers;

import com.ecobank.srms.Service.AdminService;
import com.ecobank.srms.dto.*;
import com.ecobank.srms.model.Admin;
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
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertTrue;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AdminControllerTest {
    private String globaltoken ="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJxZGZzeXJ0aXlqdHlmZHJydHlmaHI1dWk3eXRqaCIsImlzcyI6IkFueXRoaW5nIiwiZXhwIjoxNjY5OTgyMTg2fQ.7wEF1eXxixWdKwdF_3wboDbjSHeD4v88YSfS7roHaZtbPcbN6-9cxxg3w9n4fgtinKQB4pS-4JdM8Q6dSUWq_g";


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
        AdminRegisterRequest adminRegisterRequest = new AdminRegisterRequest();
        adminRegisterRequest.setUsername("TestAdminUser");
        adminRegisterRequest.setPassword("TestAdminUserpassword");
        adminRegisterRequest.setDate_Created(date);
        adminRegisterRequest.setFName("TestAdminFname");


        HttpEntity<StudentRequest> entity = new HttpEntity(adminRegisterRequest, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/v1/admin/register"), HttpMethod.POST, entity ,String.class);



        String actual = response.getBody();
        System.out.println(actual);
        //assertTrue(actual.contains("This Username Already Exists"));
        Assert.assertEquals(response.getStatusCodeValue(),201);
    }

    @Test
    void login() {
        AdminLoginRequest adminLoginRequest = new AdminLoginRequest();
        adminLoginRequest.setUsername("Admin101");
        adminLoginRequest.setPassword("Adminpassword");

        HttpEntity<StudentRequest> entity = new HttpEntity(adminLoginRequest, headers);

        ResponseEntity response = restTemplate.exchange(
                createURLWithPort("/api/v1/admin/login"), HttpMethod.POST, entity ,Object.class);

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

    @Test
    void reset() {
        AdminResetPasswordRequest adminResetPasswordRequest = new AdminResetPasswordRequest();
        adminResetPasswordRequest.setUsername("TestAdminUser");
        adminResetPasswordRequest.setNewPassword("TestAdminUserpassword");
        adminResetPasswordRequest.setConfirmPassword("TestAdminUserpassword");


        headers.setBearerAuth(globaltoken);
        System.out.println(globaltoken);

        HttpEntity entity = new HttpEntity(adminResetPasswordRequest, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/v1/admin/reset_password"), HttpMethod.POST, entity ,String.class);


        String actual = response.getBody();
        System.out.println(actual);
        assertTrue(actual.contains("Password successfully Reset"));


    }

    @Test
    void changePassword() {

        headers.setBearerAuth(globaltoken);
        AdminChangePasswordRequest adminChangePasswordRequest = new AdminChangePasswordRequest();
        adminChangePasswordRequest.setUsername("TestAdminUser");
        adminChangePasswordRequest.setNewPassword("TestAdminUserpassword_new");
        adminChangePasswordRequest.setCurrentPassword("TestAdminUserpassword");
        adminChangePasswordRequest.setConfirmPassword("TestAdminUserpassword_new");


        HttpEntity entity = new HttpEntity(adminChangePasswordRequest, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/v1/admin/change_password"), HttpMethod.POST, entity ,String.class);

        System.out.println(response.getStatusCode());


        Object obj = response.getBody();
        System.out.println(obj);

        String actual = response.getBody();
        assertTrue(actual.contains("Password successfully changed"));

    }

    @Test
    void create() {
        headers.setBearerAuth(globaltoken);
        AdminCreateCourseRequest adminCreateCourseRequest = new AdminCreateCourseRequest();
        adminCreateCourseRequest.setDepartmentname("Computer Science");
        adminCreateCourseRequest.setCourse_code("CSC9000");
        adminCreateCourseRequest.setCourse_Descr("Testing of Integration Testing");
        adminCreateCourseRequest.setLen_course("Double SEMESTER");
        adminCreateCourseRequest.setNameOfCourse("Test of Course");
        adminCreateCourseRequest.setUnit_of_course("3");
        adminCreateCourseRequest.setStatus_course("R");

        HttpEntity entity = new HttpEntity(adminCreateCourseRequest, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/v1/admin/create_courses"), HttpMethod.POST, entity ,String.class);

        System.out.println(response.getStatusCode());


        Object obj = response.getBody();
        System.out.println(obj);

        String actual = response.getBody();
        assertTrue(actual.contains("Course has been created"));
        Assert.assertEquals(response.getStatusCodeValue(),201);
    }

    @Test
    void createDept() {
        headers.setBearerAuth(globaltoken);
        AdminCreateDepartmentRequest adminCreateDepartmentRequest = new AdminCreateDepartmentRequest();
        adminCreateDepartmentRequest.setDeptName("TESTING DEPARTMENT");


        HttpEntity entity = new HttpEntity(adminCreateDepartmentRequest, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/v1/admin/create_department"), HttpMethod.POST, entity ,String.class);

        System.out.println(response.getStatusCode());


        Object obj = response.getBody();
        System.out.println(obj);

        String actual = response.getBody();

        assertTrue(actual.contains("Department has been created"));
        Assert.assertEquals(response.getStatusCodeValue(),201);

    }

    @Test
    void createStud() {
        Date date = new Date();
        headers.setBearerAuth(globaltoken);
        AdminCreateStudentRequest adminCreateStudentRequest = new AdminCreateStudentRequest();
        adminCreateStudentRequest.setJambNo("TestcreateStudent01");
        adminCreateStudentRequest.setDept_id(2L);
        adminCreateStudentRequest.setPassword("TestcreateStudent01password");
        adminCreateStudentRequest.setLevel("500");
        adminCreateStudentRequest.setDepartment("Computer Science");
        adminCreateStudentRequest.setDate_Created(date);
        adminCreateStudentRequest.setEmail("TestcreateStudent01@gmail.com");


        HttpEntity entity = new HttpEntity(adminCreateStudentRequest, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/v1/admin/create_student"), HttpMethod.POST, entity ,String.class);

        System.out.println(response.getStatusCode());


        Object obj = response.getBody();
        System.out.println(obj);

        String actual = response.getBody();
        assertTrue(actual.contains("New Student Created"));
        Assert.assertEquals(response.getStatusCodeValue(),201);

    }

    @Test
    void displayDept() {
        headers.setBearerAuth(globaltoken);
        HttpEntity entity = new HttpEntity(headers);
        List<Object> deptViewExpected = new ArrayList<>(Arrays.asList("{deptName=history, dept_id=1}, {deptName=Computer Science, dept_id=2}, {deptName=PSYCHOLOGY, dept_id=3}, {deptName=LINGUISTICS, dept_id=4}, {deptName=HISTORY, dept_id=5}, {deptName=Agriculture, dept_id=6}, {deptName=TESTING DEPARTMENT, dept_id=7}"));
        String deptviewString = deptViewExpected.toString().trim();
        List<Object> deptView = new ArrayList<>();

        ResponseEntity response = restTemplate.exchange(
                createURLWithPort("/api/v1/admin/display_dept"), HttpMethod.GET, entity ,Object.class);

        System.out.println(response.getStatusCode());


        ArrayList arrayList = (ArrayList) response.getBody();
        System.out.println(arrayList);
        //System.out.println("Response type : " + response.getBody().getClass().getName());

        if (response.getBody() instanceof ArrayList){
            deptView = (List<Object>) response.getBody();

        }

        Assert.assertEquals(deptviewString,deptView.toString().trim());

    }

    @Test
    void displayStud() {
        headers.setBearerAuth(globaltoken);
        HttpEntity entity = new HttpEntity(headers);
        List<Object> studView = new ArrayList<>();
        List<Object> studViewExpected = new ArrayList<>(Arrays.asList("{jambNo=20302030122213123, email=olamiolaaaa2221123@gmail.com, level=100, department=history, date_Created=2022-09-21}, {jambNo=400, email=Student4.com, level=400, department=Computer Science, date_Created=2022-10-13}, {jambNo=600, email=Student6.com, level=400, department=Computer Science, date_Created=2022-10-13}, {jambNo=19901990, email=Student1990.com, level=400, department=Computer Science, date_Created=2022-10-14}, {jambNo=90239023, email=Student90239023.com, level=400, department=Computer Science, date_Created=2022-10-17}, {jambNo=20099002, email=Student20099002.com, level=400, department=Computer Science, date_Created=2022-10-17}, {jambNo=18901890, email=Student189018902.com, level=400, department=Computer Science, date_Created=2022-10-17}, {jambNo=16781678, email=Student16781678.com, level=500, department=Computer Science, date_Created=2022-10-18}, {jambNo=89018901, email=Student89018901.com, level=500, department=Computer Science, date_Created=2022-10-18}, {jambNo=12344321, email=Student12344321.com, level=500, department=Computer Science, date_Created=2022-10-18}, {jambNo=30903090, email=Student30903090.com, level=500, department=Computer Science, date_Created=2022-10-18}, {jambNo=30803080, email=Student30803080.com, level=500, department=Computer Science, date_Created=2022-10-18}, {jambNo=JambTest1001, email=randomemail1001@gmail.com, level=300, department=Computer Science, date_Created=2022-11-17}, {jambNo=JambTest1034, email=randomemail1034@gmail.com, level=300, department=Computer Science, date_Created=2022-11-17}, {jambNo=TestcreateStudent01, email=TestcreateStudent01@gmail.com, level=500, department=Computer Science, date_Created=2022-11-19}"));
        String studviewString = studViewExpected.toString().trim();

        ResponseEntity response = restTemplate.exchange(
                createURLWithPort("/api/v1/admin/display_student"), HttpMethod.GET, entity ,Object.class);

        if (response.getBody() instanceof ArrayList){
            studView = (List<Object>) response.getBody();

        }

        System.out.println(response.getStatusCode());
        System.out.println(studView);

        Assert.assertEquals(studviewString,studView.toString().trim());

    }

    @Test
    void displayStudDept() {
        headers.setBearerAuth(globaltoken);

        AdminFindStudentRequest adminFindStudentRequest = new AdminFindStudentRequest();
        adminFindStudentRequest.setDeptName("Computer Science");
        List<Object> studView = new ArrayList<>();

        List<Object> studViewExpected = new ArrayList<>(Arrays.asList("{studentId=4, level=400, department=Computer Science, jambNo=400, password=$2a$10$H/yyfFKDIj9a/BgiqxQ5Q.qO7YKgzYaKEHSsv4vpGzAEOHrvo6wtC, date_Created=2022-10-13, dept_id=2, email=Student4.com}, {studentId=5, level=400, department=Computer Science, jambNo=600, password=$2a$10$WnPvqry1DTLxR4RgW6xvgueGFXxWZpuPPRGoI3vBoNncccA4676gC, date_Created=2022-10-13, dept_id=2, email=Student6.com}, {studentId=6, level=400, department=Computer Science, jambNo=19901990, password=$2a$10$P.acK3XlD.mjpfjkhOwHgu00I5KPAWjbszxETDgoepyaSBitGGrm6, date_Created=2022-10-14, dept_id=2, email=Student1990.com}, {studentId=7, level=400, department=Computer Science, jambNo=90239023, password=$2a$10$5tIkTLj74Khdfq0fdZ9wveBKeGD7aZcFXoIJAcQvvLxrQm1voDJQK, date_Created=2022-10-17, dept_id=2, email=Student90239023.com}, {studentId=8, level=400, department=Computer Science, jambNo=20099002, password=$2a$10$DP/pTPWjUdVCNIQLR/EfC.ryPORf8BHwQ2w.dELoYWB6HUZ95Ydp2, date_Created=2022-10-17, dept_id=2, email=Student20099002.com}, {studentId=9, level=400, department=Computer Science, jambNo=18901890, password=$2a$10$Ug.RT5UMteQ5A/EiuQ6eZO6/OraiSQXrOn9gMsVRFKAaDSyop/ZN., date_Created=2022-10-17, dept_id=2, email=Student189018902.com}, {studentId=10, level=500, department=Computer Science, jambNo=16781678, password=$2a$10$Mcgxuo7XnXqrVyKFCZWZKurLpjUJR8YMm1JsrMqAgrq6X9B5d81Zq, date_Created=2022-10-18, dept_id=2, email=Student16781678.com}, {studentId=11, level=500, department=Computer Science, jambNo=89018901, password=$2a$10$HSJe.xSS/gNWs5w5h6ERBe4WkRRAUBgnD7AkLrk/1rPi/CHbn4bdq, date_Created=2022-10-18, dept_id=2, email=Student89018901.com}, {studentId=12, level=500, department=Computer Science, jambNo=12344321, password=$2a$10$ck.cCodIj1uGwfTX7jkNqe6gqBo7VrvBT9tpqFT2nC0G6KVeJIIxS, date_Created=2022-10-18, dept_id=2, email=Student12344321.com}, {studentId=14, level=500, department=Computer Science, jambNo=30903090, password=$2a$10$Vq4bXr6hfY8jNdFY7mti1u1qCh4xv8pRmxyHYO1r9sPpuslF4c8Qa, date_Created=2022-10-18, dept_id=2, email=Student30903090.com}, {studentId=15, level=500, department=Computer Science, jambNo=30803080, password=$2a$10$IR49xSZXk3bDkaMWybzZqedh/z78HkfFlYsyqJej20HhdwdrpxFNW, date_Created=2022-10-18, dept_id=2, email=Student30803080.com}, {studentId=144, level=300, department=Computer Science, jambNo=JambTest1001, password=$2a$10$Ulz4ZpHeKjT2Boa9e9zvlu8nZ7N2r1ybpFmwfiAEtxN492HLgUMEe, date_Created=2022-11-17, dept_id=2, email=randomemail1001@gmail.com}, {studentId=145, level=300, department=Computer Science, jambNo=JambTest1034, password=$2a$10$It4oGHl.U3NEOgpDyNpLhOW8lwmUQFr/h0I12oKqDNITw6IEmKhda, date_Created=2022-11-17, dept_id=2, email=randomemail1034@gmail.com}, {studentId=146, level=500, department=Computer Science, jambNo=TestcreateStudent01, password=$2a$10$BWaBLvJ8CSZr4D94oFamwenGxoeUvlfLciq3GpOfqrzUcCwJBQ/.a, date_Created=2022-11-19, dept_id=2, email=TestcreateStudent01@gmail.com}"));
        String studviewString = studViewExpected.toString().trim();
        HttpEntity entity = new HttpEntity(adminFindStudentRequest,headers);

        ResponseEntity response = restTemplate.exchange(
                createURLWithPort("/api/v1/admin/display_student_dept"), HttpMethod.POST, entity ,Object.class);

        if (response.getBody() instanceof ArrayList){
            studView = (List<Object>) response.getBody();

        }
        System.out.println(studView);
        Assert.assertEquals(studviewString,studView.toString().trim());
    }

    @Test
    void displayCountStud() {
        headers.setBearerAuth(globaltoken);
        HttpEntity entity = new HttpEntity(headers);
        LinkedHashMap response_map = new LinkedHashMap<>();
        int studExpected = 15;

        ResponseEntity response = restTemplate.exchange(
                createURLWithPort("/api/v1/admin/count_all_students"), HttpMethod.GET, entity ,Object.class);
        System.out.println(response.getBody().getClass().getName());
        if (response.getBody() instanceof LinkedHashMap){
            response_map = (LinkedHashMap) response.getBody();
        }
        System.out.println(response.getBody().toString());
         int studActual = (int) response_map.get("count");
        Assert.assertEquals(studExpected,studActual);


    }

    @Test
    void displayCountDept() {

        headers.setBearerAuth(globaltoken);
        HttpEntity entity = new HttpEntity(headers);
        LinkedHashMap response_map = new LinkedHashMap<>();
        int studExpected = 7;
        ResponseEntity response = restTemplate.exchange(
                createURLWithPort("/api/v1/admin/count_all_departments"), HttpMethod.GET, entity ,Object.class);
        if (response.getBody() instanceof LinkedHashMap){
            response_map = (LinkedHashMap) response.getBody();
        }
        int studActual = (int) response_map.get("count");
        Assert.assertEquals(studExpected,studActual);

    }

    @Test
    void countStudDept() {
        headers.setBearerAuth(globaltoken);

        AdminCountStudDeptRequest adminCountStudDeptRequest = new AdminCountStudDeptRequest();
        adminCountStudDeptRequest.setDeptName("Computer Science");
        LinkedHashMap response_map = new LinkedHashMap<>();
        int studExpected = 14;
        HttpEntity entity = new HttpEntity(adminCountStudDeptRequest, headers);
        ResponseEntity response = restTemplate.exchange(
                createURLWithPort("/api/v1/admin/count_student_dept"), HttpMethod.POST, entity ,Object.class);
        //System.out.println(response.get);

        if (response.getBody() instanceof  LinkedHashMap){
            response_map = (LinkedHashMap) response.getBody();
        }
        int studActual = (int) response_map.get("count");
        Assert.assertEquals(studExpected,studActual);

    }

    @Test
    void displayStudLevel() {
        headers.setBearerAuth(globaltoken);
        AdminFindStudentRequest adminFindStudentRequest = new AdminFindStudentRequest();
        adminFindStudentRequest.setDeptName("Computer Science");
        HttpEntity entity = new HttpEntity(adminFindStudentRequest, headers);
        LinkedHashMap response_map = new LinkedHashMap<>();
        List<Object> studViewExpected = new ArrayList<>(Arrays.asList("[300, 2], [400, 6], [500, 6]"));

        // LinkedHashMap expectedResponse = new LinkedHashMap(Integer.parseInt("{code=00, response=Successful, list=[[300, 2], [400, 6], [500, 6]], message=These are the number of students in the department by level}")));
        //System.out.println(expectedResponse);

        ResponseEntity response = restTemplate.exchange(
                createURLWithPort("/api/v1/admin/show_count_all_level_by_department"), HttpMethod.POST, entity ,Object.class);


        System.out.println(response.getBody().getClass().getName());
        System.out.println(response.getBody());

        if (response.getBody() instanceof LinkedHashMap){
            response_map = (LinkedHashMap) response.getBody();
        }
        ArrayList reponse_get = (ArrayList) response_map.get("list");


        Assert.assertEquals(studViewExpected.toString().trim(),reponse_get.toString().trim());

    }

    @Test
    void displayCountStudbyDept() {
        headers.setBearerAuth(globaltoken);
        HttpEntity entity = new HttpEntity(headers);
        List<Object> studView = new ArrayList<>();
        List<Object> studViewExpected = new ArrayList<>(Arrays.asList("[[PSYCHOLOGY, 0], [LINGUISTICS, 0], [HISTORY, 0], [Agriculture, 0], [Computer Science, 14], [TESTING DEPARTMENT, 0], [history, 1]]"));

        String studviewString = studViewExpected.toString().trim();
        ResponseEntity response = restTemplate.exchange(
                createURLWithPort("/api/v1/admin/count_all_students_by_departments"), HttpMethod.GET, entity ,Object.class);

        if (response.getBody() instanceof ArrayList){
            studView = (List<Object>) response.getBody();

        }
        Assert.assertEquals(studviewString,studView.toString().trim());
    }

    @Test
    void showNumberOldStudents() {
        headers.setBearerAuth(globaltoken);
        HttpEntity entity = new HttpEntity(headers);
        LinkedHashMap response_map = new LinkedHashMap<>();
        int studExpected = 4;
        ResponseEntity response = restTemplate.exchange(
                createURLWithPort("/api/v1/admin/show_number_all_old_students"), HttpMethod.GET, entity ,Object.class);
        //System.out.println(response.get);

        if (response.getBody() instanceof  LinkedHashMap){
            response_map = (LinkedHashMap) response.getBody();
        }
        int studActual = (int) response_map.get("count");
        Assert.assertEquals(studExpected,studActual);

    }

    @Test
    void showNumberNewStudents() {
        headers.setBearerAuth(globaltoken);
        HttpEntity entity = new HttpEntity(headers);
        LinkedHashMap response_map = new LinkedHashMap<>();
        int studExpected = 11;
        ResponseEntity response = restTemplate.exchange(
                createURLWithPort("/api/v1/admin/show_number_all_new_students"), HttpMethod.GET, entity ,Object.class);
        //System.out.println(response.get);

        if (response.getBody() instanceof  LinkedHashMap){
            response_map = (LinkedHashMap) response.getBody();
        }
        int studActual = (int) response_map.get("count");
        Assert.assertEquals(studExpected,studActual);

    }
}