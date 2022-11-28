package com.ecobank.srms.repository;

import com.ecobank.srms.SrmsApplication;
import com.ecobank.srms.model.Courses;
import com.ecobank.srms.model.Department;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = SrmsApplication.class)
@ComponentScan(basePackages = "com.ecobank.srms")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CourseRepositoryTest {

    @Autowired
    CourseRepository courseRepository;

    @Test
    void findByCourseId() {

        Courses courses_test = new Courses();
        courses_test.setCourse_code("TestCode123");
        courses_test.setCourse_Descr("Testing of Course");
        courses_test.setLen_course("Double Semester");
        courses_test.setNameOfCourse("TestCourse");
        courses_test.setUnit_of_course("3");
        courses_test.setStatus_course("R");
        courses_test.setDepartmentname("COMPUTER SCIENCE");

        courseRepository.save(courses_test);


        Courses courses = courseRepository.findByCourseId(courses_test.getCourseId());
        assertEquals(courses,courses_test);
    }

    @Test
    void findAllByNameOfCourse() {
        Courses courses_test = new Courses();
        courses_test.setCourse_code("TestCode123");
        courses_test.setCourse_Descr("Testing of Course");
        courses_test.setLen_course("Double Semester");
        courses_test.setNameOfCourse("TestCourse");
        courses_test.setUnit_of_course("3");
        courses_test.setStatus_course("R");
        courses_test.setDepartmentname("COMPUTER SCIENCE");

        courseRepository.save(courses_test);


        Courses courses = courseRepository.findAllByNameOfCourse(courses_test.getNameOfCourse());
        assertEquals(courses,courses_test);
    }

    @Test
    void findAllBydepartmentname() {
        List<Courses> coursesList = courseRepository.findAllBydepartmentname("COMPUTER SCIENCE");
        //Courses courses = new Courses(1L,"");

        List<Courses> coursesTest = new ArrayList<>(Arrays.asList(
                new Courses(1L,"CSC202","PYTHON ENGINEERING","Description of python for beginners","1st SEMESTER","3","R","COMPUTER SCIENCE"),
                new Courses(2L,"CSC203","Java Engineering","Description of Java for beginners","1st SEMESTER","2","R","COMPUTER SCIENCE")
                ,new Courses(3L,"CSC214","React native for beginners","Description of react native  for beginners","2nd SEMESTER","2","R","COMPUTER SCIENCE")
                ,new Courses(4L,"CSC230","Springboot for beginners","Description of Springboot for beginners","Double SEMESTER","3","R","COMPUTER SCIENCE")
                ,new Courses(17L,"TestCode123","TestCourse","Testing of Course","Double Semester","3","R","COMPUTER SCIENCE")));

        assertEquals(coursesTest,coursesList);




        /*
        * [Courses(courseId=1, course_code=CSC202, nameOfCourse=PYTHON ENGINEERING, course_Descr=Description of python for beginners, len_course=1st SEMESTER, unit_of_course=3, status_course=R, departmentname=COMPUTER SCIENCE),
        * Courses(courseId=2, course_code=CSC203, nameOfCourse=Java Engineering, course_Descr=Description of Java for beginners, len_course=1st SEMESTER, unit_of_course=2, status_course=R, departmentname=COMPUTER SCIENCE),
        * Courses(courseId=3, course_code=CSC214, nameOfCourse=React native for beginners, course_Descr=Description of react native  for beginners, len_course=2nd SEMESTER, unit_of_course=2, status_course=R, departmentname=COMPUTER SCIENCE),
        *  Courses(courseId=4, course_code=CSC230, nameOfCourse=Springboot for beginners, course_Descr=Description of Springboot for beginners, len_course=Double SEMESTER, unit_of_course=3, status_course=R, departmentname=COMPUTER SCIENCE),
        *  Courses(courseId=14, course_code=TestCode123, nameOfCourse=TestCourse, course_Descr=Testing of Course, len_course=Double Semester, unit_of_course=3, status_course=R, departmentname=COMPUTER SCIENCE)]
        *
        * */

    }
}