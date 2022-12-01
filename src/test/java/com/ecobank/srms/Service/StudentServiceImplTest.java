package com.ecobank.srms.Service;

import com.ecobank.srms.SrmsApplication;
import com.ecobank.srms.dto.*;
import com.ecobank.srms.model.Student;
import com.ecobank.srms.repository.StudentRepository;
import com.ecobank.srms.utils.JwtUtils;
import com.ecobank.srms.utils.Token;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SrmsApplication.class)
@ComponentScan(basePackages = "com.ecobank.srms")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StudentServiceImplTest {
    Logger logger = Logger.getLogger(StudentServiceImplTest.class.getName());

    private String globalToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJxZGZzeXJ0aXlqdHlmZHJydHlmaHI1dWk3eXRqaCIsImlzcyI6IkFueXRoaW5nIiwiZXhwIjoxNjY5NjM2MTc2fQ.SVR2NBk2wLiUgMS40sKUY5_qZhbrhijJF0VRATgAePuLjNAt9qjQ3Lazs9wVFUi6RBm6OOrs5J3rfNkF0axVWw";

@Autowired
    private StudentRepository studentRepository;


    @Autowired
    private StudentService studentService;

    @Autowired
    HttpServletRequest httpServletRequest;

    @Autowired
    JwtUtils jwtUtils;

//    @MockBean
//    @Autowired
//    private StudentService studentService;



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

        StudentResponse studentResponse_expected = studentService.Register(studentRequest);
        System.out.println(studentResponse_expected);
        StudentResponse studentResponse_actual = StudentResponse.builder()
                .message("This registration exists please sign in")
                .token(null)
                .jambNo(null)
                .level(null)
                .department(null)
                .email(null)
                .jambNo(null)
                .build();

        Assert.assertEquals(studentResponse_expected,studentResponse_actual);
    }

    @Test
    void login() throws IOException {

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setPassword("JambTest1034");
        loginRequest.setJambNo("JambTest1034");
        Student student= new Student();
        student = studentRepository.findByJambNo("JambTest1034");
        StudentResponse studentResponse_expected = studentService.Login(loginRequest);

        logger.info("Student Response :" + studentResponse_expected);
        logger.info("Login Request "  + loginRequest);
        logger.info("student " + student);
        System.out.println(studentResponse_expected.getToken());

        StudentResponse studentResponse_actual = StudentResponse.builder()
                .jambNo("JambTest1034")
                .message("Login Successful")
                .token(studentResponse_expected.getToken())
                .department("Computer Science")
                .level("300")
                .email("randomemail1034@gmail.com")
                .build();

        Assert.assertEquals(studentResponse_expected,studentResponse_actual);

    }

    @Test
    void updateCurrentPassword() {
        ChangePasswordRequest changePasswordRequest = new ChangePasswordRequest("JambTest1034","JambTest1034","JambTest1034_new","JambTest1034_new");
        ChangePasswordResponse changePasswordResponse_actual = studentService.updateCurrentPassword(changePasswordRequest);

        ChangePasswordResponse changePasswordResponse_expected = new ChangePasswordResponse("Password successfully changed");

        Assert.assertEquals(changePasswordResponse_expected,changePasswordResponse_actual);
    }

    @Test
    void extractToken() {
       Token token =  studentService.extractToken(httpServletRequest);
       Assert.assertNotNull(token.getAccessToken());

    }

    @Test
    void reset() {
        ResetPasswordRequest resetPasswordRequest = new ResetPasswordRequest("JambTest1034","JambTest1034","JambTest1034");
        ResetPasswordResponse resetPasswordResponse_actual = studentService.reset(resetPasswordRequest);

        ResetPasswordResponse resetPasswordResponse_expected = new ResetPasswordResponse("Password successfully Reset");

        Assert.assertEquals(resetPasswordResponse_expected,resetPasswordResponse_actual);

    }

    @Test
    void displayStud() {
        Object object_actual = studentService.displayStud();
        List<Object> studViewExpected = new ArrayList<>(Arrays.asList("ViewStudent(jambNo=20302030122213123, email=olamiolaaaa2221123@gmail.com, level=100, department=history, date_Created=2022-09-21), ViewStudent(jambNo=400, email=Student4.com, level=400, department=Computer Science, date_Created=2022-10-13), ViewStudent(jambNo=600, email=Student6.com, level=400, department=Computer Science, date_Created=2022-10-13), ViewStudent(jambNo=19901990, email=Student1990.com, level=400, department=Computer Science, date_Created=2022-10-14), ViewStudent(jambNo=90239023, email=Student90239023.com, level=400, department=Computer Science, date_Created=2022-10-17), ViewStudent(jambNo=20099002, email=Student20099002.com, level=400, department=Computer Science, date_Created=2022-10-17), ViewStudent(jambNo=18901890, email=Student189018902.com, level=400, department=Computer Science, date_Created=2022-10-17), ViewStudent(jambNo=16781678, email=Student16781678.com, level=500, department=Computer Science, date_Created=2022-10-18), ViewStudent(jambNo=89018901, email=Student89018901.com, level=500, department=Computer Science, date_Created=2022-10-18), ViewStudent(jambNo=12344321, email=Student12344321.com, level=500, department=Computer Science, date_Created=2022-10-18), ViewStudent(jambNo=30903090, email=Student30903090.com, level=500, department=Computer Science, date_Created=2022-10-18), ViewStudent(jambNo=30803080, email=Student30803080.com, level=500, department=Computer Science, date_Created=2022-10-18), ViewStudent(jambNo=JambTest1001, email=randomemail1001@gmail.com, level=300, department=Computer Science, date_Created=2022-11-17), ViewStudent(jambNo=TestcreateStudent01, email=TestcreateStudent01@gmail.com, level=500, department=Computer Science, date_Created=2022-11-19), ViewStudent(jambNo=JambTest1034, email=randomemail1034@gmail.com, level=300, department=Computer Science, date_Created=2022-11-17)"));

        Assert.assertEquals(studViewExpected.toString().trim(),object_actual.toString().trim());

    }

    @Test
    void countStud() throws IOException {
        AdminCountStudDisplayResponse adminCountStudDisplayResponse_actual = studentService.countStud();

        AdminCountStudDisplayResponse adminCountStudDisplayResponse_expected = AdminCountStudDisplayResponse
                .builder()
                .count(15L)
                .message("Successful")
                .response("These are the number of Students")
                .build();

        Assert.assertEquals(adminCountStudDisplayResponse_expected,adminCountStudDisplayResponse_actual);
    }

    @Test
    void displayCountStudbyDept() {
        List<Object> student_test = studentService.displayCountStudbyDept();
        System.out.println(Collections.singletonList(student_test));
        ArrayList<String> test_list = new ArrayList<>();
        ArrayList<String> new_list = new ArrayList<>();

        for (int i = 0; i<student_test.size();i++){
            test_list.add(student_test.get(i).toString());
        }
        System.out.println(test_list);
    }

    @Test
    void showCountLevelByDepartment() {
        AdminFindStudentRequest adminFindStudentRequest = new AdminFindStudentRequest();
        adminFindStudentRequest.setDeptName("Computer Science");

        AdminFindStudentResponse adminFindStudentResponse_expected = studentService.showCountLevelByDepartment(adminFindStudentRequest);
        System.out.println(adminFindStudentResponse_expected);
        System.out.println(adminFindStudentResponse_expected.getList().toString());

        List deptList = studentRepository.findLevelByDepartmentAndStudent(adminFindStudentRequest.getDeptName());
        System.out.println(deptList);

        AdminFindStudentResponse adminFindStudentResponse_actual = AdminFindStudentResponse.builder()
                .code("00")

                .message("These are the number of students in the department by level")
                .response("Successful")
                .build();


    }

    @Test
    void showCountNewStudents() {
        AdminStudentGeneralResponse adminStudentGeneralResponse_expected = studentService.ShowCountNewStudents();

        AdminStudentGeneralResponse adminStudentGeneralResponse_actual = AdminStudentGeneralResponse
                .builder()
                .code("00")
                .message("These are the number of students after 2022-10-17")
                .response("Successful")
                .count(11L)
                .build();

        Assert.assertEquals(adminStudentGeneralResponse_actual,adminStudentGeneralResponse_expected);
    }

    @Test
    void showCountOldStudents() {
        AdminStudentGeneralResponse adminStudentGeneralResponse_expected = studentService.ShowCountOldStudents();

        AdminStudentGeneralResponse adminStudentGeneralResponse_actual = AdminStudentGeneralResponse
                .builder()
                .code("00")
                .message("These are the number of students before 2022-10-17")
                .response("Successful")
                .count(4L)
                .build();

        Assert.assertEquals(adminStudentGeneralResponse_actual,adminStudentGeneralResponse_expected);
    }
}