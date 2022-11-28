package com.ecobank.srms.controllers;

import com.ecobank.srms.Service.CourseManageService;
import com.ecobank.srms.dto.CourseRegisterRequest;
import com.ecobank.srms.dto.CoursesDisplayRequest;
import com.ecobank.srms.dto.StudentDeleteCourseRequest;
import com.ecobank.srms.dto.ViewCoursesRequest;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CourseControllerTest {
    private String globaltoken ="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJxZGZzeXJ0aXlqdHlmZHJydHlmaHI1dWk3eXRqaCIsImlzcyI6IkFueXRoaW5nIiwiZXhwIjoxNjY5MTMzMjQ3fQ.78N0-MrQCqf7S_uSGzclYE_wYVWQ2O0Dm5BFq0ETJT8kKkEJHsEC0maSrb_0gOFb6mjMt2XAofuPLfhivp9WPw";


    @Autowired
    HttpServletRequest httpServletRequest;

    @Autowired
    CourseManageService courseManageService;

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
    void saveCoursePerStudent() {
        headers.setBearerAuth(globaltoken);
        CourseRegisterRequest courseRegisterRequest = new CourseRegisterRequest();
        courseRegisterRequest.setCourses(new ArrayList<>(Arrays.asList(17L,18L)));
        courseRegisterRequest.setJambNo("JambTest1001");
        List expectedMap = new ArrayList<>(Arrays.asList("{resp_code=99, resp_msg=Course TestCourse has already been registered, data={courseManageId=null, studReg=JambTest1001, course_Id=17, course_Name=TestCourse}}, {resp_code=99, resp_msg=Course Test of Course has already been registered, data={courseManageId=null, studReg=JambTest1001, course_Id=18, course_Name=Test of Course}}"));
        String messageActual = "Course TestCourse has already been registered";
        String codeActual = "99";
        //LinkedHashMap response_map = new LinkedHashMap<>();
        HttpEntity entity = new HttpEntity(courseRegisterRequest,headers);
        ResponseEntity response = restTemplate.exchange(
                createURLWithPort("/api/v1/student/register_course"), HttpMethod.POST, entity ,Object.class);
        System.out.println(response.getBody().getClass().getName());

        //Object response_map = new Object();
        List response_map = new ArrayList<>();
        if (response.getBody() instanceof java.util.ArrayList){
            response_map = (List<Object>) response.getBody();
        }
        System.out.println(response_map);

      Assert.assertEquals(expectedMap.toString().trim(),response_map.toString().trim());

    }

    @Test
    void view() {
        headers.setBearerAuth(globaltoken);
        ViewCoursesRequest viewCoursesRequest = new ViewCoursesRequest();
        viewCoursesRequest.setRegNo("JambTest1001");
        ArrayList expectedMap = new ArrayList<>(Arrays.asList("{courseCode=TestCode123, courseName=TestCourse, status=R, unit=3, courseId=17, courseDesc=null}, {courseCode=CSC9000, courseName=Test of Course, status=R, unit=3, courseId=18, courseDesc=null}"));
        HttpEntity entity = new HttpEntity(viewCoursesRequest,headers);
        ResponseEntity response = restTemplate.exchange(
                createURLWithPort("/api/v1/student/view_course"), HttpMethod.POST, entity ,Object.class);
        System.out.println(response.getBody().getClass().getName());

        List response_map = new ArrayList<>();
        if (response.getBody() instanceof java.util.ArrayList){
            response_map = (List<Object>) response.getBody();
        }
        System.out.println(response_map);

        Assert.assertEquals(expectedMap.toString().trim(),response_map.toString().trim());
    }

    @Test
    void getCoursebyDepartment() {
        headers.setBearerAuth(globaltoken);
        CoursesDisplayRequest coursesDisplayRequest =  new CoursesDisplayRequest();
        String actual = "[{\"courseCode\":\"CSC202\",\"courseName\":\"PYTHON ENGINEERING\",\"status\":\"R\",\"unit\":\"3\",\"courseId\":1,\"courseDesc\":\"Description of python for beginners\"},{\"courseCode\":\"CSC203\",\"courseName\":\"Java Engineering\",\"status\":\"R\",\"unit\":\"2\",\"courseId\":2,\"courseDesc\":\"Description of Java for beginners\"},{\"courseCode\":\"CSC214\",\"courseName\":\"React native for beginners\",\"status\":\"R\",\"unit\":\"2\",\"courseId\":3,\"courseDesc\":\"Description of react native  for beginners\"},{\"courseCode\":\"CSC230\",\"courseName\":\"Springboot for beginners\",\"status\":\"R\",\"unit\":\"3\",\"courseId\":4,\"courseDesc\":\"Description of Springboot for beginners\"},{\"courseCode\":\"TestCode123\",\"courseName\":\"TestCourse\",\"status\":\"R\",\"unit\":\"3\",\"courseId\":17,\"courseDesc\":\"Testing of Course\"},{\"courseCode\":\"CSC9000\",\"courseName\":\"Test of Course\",\"status\":\"R\",\"unit\":\"3\",\"courseId\":18,\"courseDesc\":\"Testing of Integration Testing\"}]";
        coursesDisplayRequest.setDepartment_name("Computer Science");
        HttpEntity entity = new HttpEntity(coursesDisplayRequest,headers);
        ResponseEntity response = restTemplate.exchange(
                createURLWithPort("/api/v1/student/view_All_course"), HttpMethod.POST, entity ,String.class);
        System.out.println(response.getBody().getClass().getName());
        System.out.println(response.getBody());

        Assert.assertEquals(response.getBody().toString().trim(),actual.trim());

    }

    @Test
    void deleteCourse() {
        headers.setBearerAuth(globaltoken);
        StudentDeleteCourseRequest studentDeleteCourseRequest = new StudentDeleteCourseRequest();
        studentDeleteCourseRequest.setJambNo("JambTest1001");
        studentDeleteCourseRequest.setCourse_Id(18L);
        String actual = "{\"message\":\"The student hasn't registered for this course\"}";
        HttpEntity entity = new HttpEntity(studentDeleteCourseRequest,headers);
        ResponseEntity response = restTemplate.exchange(
                createURLWithPort("/api/v1/student/delete_course"), HttpMethod.POST, entity ,String.class);
        System.out.println(response.getBody().getClass().getName());
        System.out.println(response.getBody());


        Assert.assertEquals(response.getBody().toString().trim(),actual.trim());
    }
}