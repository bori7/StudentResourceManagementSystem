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
class DepartmentRepositoryTest {


    @Autowired
    private DepartmentRepository departmentRepository;

    @Test
    void findByDeptName() {
//        Department department = new Department();
//        department.setDeptName("Agriculture");
//        departmentRepository.save(department);
        Department department_test = departmentRepository.findByDeptName("Computer Science");
        System.out.println(department_test);
        assertNotNull(department_test);
        //assertEquals(department_test,department);
    }

    @Test
    void findAll() {
        List<Department> dept_list = departmentRepository.findAll(); //All departments
        //System.out.println(dept_list);



        List<Department> department_test = new ArrayList<>(Arrays.asList(
                new Department(1L,"history"),
                new Department(2L,"Computer Science"),
                new Department(3L,"PSYCHOLOGY"),
                new Department(4L,"LINGUISTICS"),
                new Department(5L,"HISTORY"),
                new Department(6L,"Agriculture")
        )
        );

        assertEquals(dept_list,department_test);



//        System.out.println(department_test);



        /*
        [Department(Dept_id=1, deptName=history),
        Department(Dept_id=2, deptName=Computer Science),
         Department(Dept_id=3, deptName=PSYCHOLOGY),
         Department(Dept_id=4, deptName=LINGUISTICS),
         Department(Dept_id=5, deptName=HISTORY),
         Department(Dept_id=6, deptName=Agriculture)]
        */



    }

    @Test
    void count() {
        long count = departmentRepository.count();
        long count_test = 6L;
        assertEquals(count_test,count);
    }
}