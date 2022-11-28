package com.ecobank.srms.repository;

import com.ecobank.srms.SrmsApplication;
import com.ecobank.srms.model.Admin;
import com.ecobank.srms.model.Teacher;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = SrmsApplication.class)
@ComponentScan(basePackages = "com.ecobank.srms")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TeacherRepositoryTest {

    @Autowired
    TeacherRepository teacherRepository;

    @Test
    void findByUsername() {
        Date date = new Date();
        Teacher teacher = new Teacher();
        teacher.setUsername("TestTeacherUser");
        teacher.setPassword("TestTeacherEmailUserpassword");
        teacher.setDate_Created(date);
        teacher.setEmail("TestEmail@gmail.com");
        teacherRepository.save(teacher);
       Optional<Teacher> teacher_test = teacherRepository.findByUsername("TestTeacherUser");
        assertEquals(teacher,teacher_test.get());
    }

    @Test
    void findByEmail() {
        Date date = new Date();
        Teacher teacher = new Teacher();
        teacher.setUsername("TestTeacherUser1");
        teacher.setPassword("TestTeacherEmailUserpassword1");
        teacher.setDate_Created(date);
        teacher.setEmail("TestEmail@gmail.com1");
        teacherRepository.save(teacher);
        Optional<Teacher> teacher_test = teacherRepository.findByEmail("TestEmail@gmail.com1");
        assertEquals(teacher,teacher_test.get());
    }
}