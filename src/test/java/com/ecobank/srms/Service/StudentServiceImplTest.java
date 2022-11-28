package com.ecobank.srms.Service;

import com.ecobank.srms.SrmsApplication;
import com.ecobank.srms.dto.LoginRequest;
import com.ecobank.srms.dto.StudentResponse;
import com.ecobank.srms.model.Student;
import com.ecobank.srms.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SrmsApplication.class)
@ComponentScan(basePackages = "com.ecobank.srms")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StudentServiceImplTest {
    Logger logger = Logger.getLogger(StudentServiceImplTest.class.getName());

    @MockBean
    private StudentRepository studentRepository;

    @MockBean
    @Autowired
    private StudentServiceImpl studentService;

//    @MockBean
//    @Autowired
//    private StudentService studentService;



    @Test
    void register() {

    }

    @Test
    void login() throws IOException {

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setPassword("JambTest1034");
        loginRequest.setJambNo("JambTest1034");
        Student student= new Student();
        student = studentRepository.findByJambNo("JambTest1034");
        StudentResponse studentResponse = studentService.Login(loginRequest);

        logger.info("Student Response :" + studentResponse);
        logger.info("Login Request "  + loginRequest);
        logger.info("student " + student);


    }

    @Test
    void updateCurrentPassword() {

    }

    @Test
    void extractToken() {

    }

    @Test
    void reset() {

    }

    @Test
    void displayStud() {

    }

    @Test
    void countStud() {

    }

    @Test
    void displayCountStudbyDept() {

    }

    @Test
    void showCountLevelByDepartment() {

    }

    @Test
    void showCountNewStudents() {

    }

    @Test
    void showCountOldStudents() {

    }
}