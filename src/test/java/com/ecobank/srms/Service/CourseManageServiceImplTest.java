package com.ecobank.srms.Service;

import com.ecobank.srms.SrmsApplication;
import com.ecobank.srms.dto.*;
import com.ecobank.srms.model.CourseManage;
import com.ecobank.srms.model.Courses;
import com.ecobank.srms.model.ViewCourse;
import org.junit.Assert;
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
class CourseManageServiceImplTest {

  @Autowired
  CourseManageService courseManageService;


    @Test
    void getCourseName() {
        String courseName = courseManageService.getCourseName(19L);
        Assert.assertEquals(courseName,"Test of AdminService Course");
    }

    @Test
    void saveCoursePerStudent() {
      CourseRegisterRequest courseRegisterRequest = new CourseRegisterRequest();
      courseRegisterRequest.setCourses(new ArrayList<>(Arrays.asList(17L,18L)));
      courseRegisterRequest.setJambNo("JambTest1001");
      List <CourseManageResponse> courseManageResponses = courseManageService.saveCoursePerStudent(courseRegisterRequest);

      List expectedMap = new ArrayList<>(Arrays.asList("{resp_code=00, resp_msg=Course Saved, data={courseManageId=null, studReg=JambTest1001, course_Id=17, course_Name=TestCourse}}, {resp_code=00, resp_msg=Course Saved, data={courseManageId=null, studReg=JambTest1001, course_Id=18, course_Name=Test of Course}}"));

      Assert.assertEquals(courseManageResponses.toString().trim(),expectedMap.toString().trim());
    }

    @Test
    void view() throws Exception {
      ViewCoursesRequest viewCoursesRequest = new ViewCoursesRequest();
      viewCoursesRequest.setRegNo("JambTest1001");
      List response_map = new ArrayList<>();
      response_map = (List<Object>) courseManageService.view(viewCoursesRequest);

      ArrayList expectedMap = new ArrayList<>(Arrays.asList("{courseCode=TestCode123, courseName=TestCourse, status=R, unit=3, courseId=17, courseDesc=null}, {courseCode=CSC9000, courseName=Test of Course, status=R, unit=3, courseId=18, courseDesc=null}"));
      Assert.assertEquals(expectedMap,response_map);
    }

    @Test
    void getAll() {
       Courses course_actual =  courseManageService.getAll("Test of AdminService Course");
      Courses couses_expected =  new Courses();
      couses_expected.setCourse_code("CSC9001");
      couses_expected.setCourse_Descr("Testing of Integration Testing through Service");
      couses_expected.setCourseId(19L);
      couses_expected.setDepartmentname("COMPUTER SCIENCE");
      couses_expected.setLen_course("Double SEMESTER");
      couses_expected.setNameOfCourse("Test of AdminService Course");
      couses_expected.setStatus_course("R");
      couses_expected.setUnit_of_course("3");

      Assert.assertEquals(course_actual,couses_expected);


    }

    @Test
    void getCoursebyDepartment() {
      CoursesDisplayRequest coursesDisplayRequest =  new CoursesDisplayRequest();
      coursesDisplayRequest.setDepartment_name("Computer Science");
      String actual =
"[ViewCourse(courseCode=CSC202, courseName=PYTHON ENGINEERING, status=R, unit=3, courseId=1, courseDesc=Description of python for beginners), ViewCourse(courseCode=CSC203, courseName=Java Engineering, status=R, unit=2, courseId=2, courseDesc=Description of Java for beginners), ViewCourse(courseCode=CSC214, courseName=React native for beginners, status=R, unit=2, courseId=3, courseDesc=Description of react native  for beginners), ViewCourse(courseCode=CSC230, courseName=Springboot for beginners, status=R, unit=3, courseId=4, courseDesc=Description of Springboot for beginners), ViewCourse(courseCode=TestCode123, courseName=TestCourse, status=R, unit=3, courseId=17, courseDesc=Testing of Course), ViewCourse(courseCode=CSC9000, courseName=Test of Course, status=R, unit=3, courseId=18, courseDesc=Testing of Integration Testing), ViewCourse(courseCode=CSC9001, courseName=Test of AdminService Course, status=R, unit=3, courseId=19, courseDesc=Testing of Integration Testing through Service)]";

       Object expected = courseManageService.getCoursebyDepartment(coursesDisplayRequest);

     Assert.assertEquals(actual,expected.toString());

    }

    @Test
    void studDelete() {
      StudentDeleteCourseRequest studentDeleteCourseRequest = new StudentDeleteCourseRequest();
      studentDeleteCourseRequest.setJambNo("JambTest1001");
      studentDeleteCourseRequest.setCourse_Id(18L);
      StudentDeleteCourseResponse  studentDeleteCourseResponse_actual =  courseManageService.studDelete(studentDeleteCourseRequest);
      StudentDeleteCourseResponse studentDeleteCourseResponse_expected = StudentDeleteCourseResponse
              .builder()
              .message("Course Deleted")
              .build();

      Assert.assertEquals(studentDeleteCourseResponse_actual,studentDeleteCourseResponse_expected);
    }
}