package com.ecobank.srms.repository;

import com.ecobank.srms.SrmsApplication;
import com.ecobank.srms.model.CourseManage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = SrmsApplication.class)
@ComponentScan(basePackages = "com.ecobank.srms")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CourseManageRepositoryTest {


    @Autowired
    CourseManageRepository courseManageRepository;

    @Test
    void findById() {
        CourseManage courseManage = new CourseManage();
        courseManage.setStudReg("TestStudent");
        courseManage.setCourse_Name("TestName");
        courseManage.setCourse_Id(100L);
        courseManageRepository.save(courseManage);

        Optional<CourseManage> courseManage_test = courseManageRepository.findById(courseManage.getCourseManageId());
        assertEquals(courseManage,courseManage_test.get());
    }

    @Test
    void findByStudReg() {
        CourseManage courseManage = new CourseManage();
        courseManage.setStudReg("TestStudent");
        courseManage.setCourse_Name("TestName2");
        courseManage.setCourse_Id(101L);
        courseManageRepository.save(courseManage);



        List courseManage_Test = courseManageRepository.findByStudReg("TestStudent");
        System.out.println(courseManage_Test);

        List <CourseManage> courseManages = new ArrayList<>(Arrays.asList(
                new CourseManage(122L,"TestStudent",100L,"TestName"),courseManage));

        assertEquals(courseManage_Test,courseManages);

    }

    @Test
    void deleteBycourseId() {

        courseManageRepository.deleteBycourseId(101L,"TestStudent");
        List <CourseManage> courseManages = courseManageRepository.findByCourseIdAndStudReg(101L,"TestStudent");
        assertEquals(0,courseManages.size());

    }

    @Test
    void findByCourseIdAndStudReg() {
        CourseManage courseManage = new CourseManage();
        courseManage.setStudReg("TestStudent");
        courseManage.setCourse_Name("TestName201");
        courseManage.setCourse_Id(103L);

        CourseManage courseManage1 = new CourseManage();
        courseManage1.setStudReg("TestStudent");
        courseManage1.setCourse_Name("TestName101");
        courseManage1.setCourse_Id(104L);
        courseManageRepository.save(courseManage1);
        courseManageRepository.save(courseManage);

        List<CourseManage> courseManageList = courseManageRepository.findByCourseIdAndStudReg(104L,"TestStudent");
        assertEquals(1,courseManageList.size());
    }
}