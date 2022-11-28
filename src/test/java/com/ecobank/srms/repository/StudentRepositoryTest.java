package com.ecobank.srms.repository;

import com.ecobank.srms.SrmsApplication;
import com.ecobank.srms.controllers.StudentController;
import com.ecobank.srms.model.Student;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;

@RunWith(SpringRunner.class)
//@DataJpaTest
@SpringBootTest(classes = SrmsApplication.class)
@ComponentScan(basePackages = "com.ecobank.srms")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StudentRepositoryTest {


    Logger logger = Logger.getLogger(StudentController.class.getName());



    @Autowired
    private StudentRepository studentRepository;


    public void delete() {
//        studentRepository.deleteById(16L);
//        studentRepository.deleteById(17L);
//        studentRepository.deleteById(18L);
//        studentRepository.deleteById(19L);
        studentRepository.deleteByJambNo("JambTest1001");
        studentRepository.deleteByJambNo("JambTest1023");
        studentRepository.deleteByJambNo("JambTest1024");
        studentRepository.deleteByJambNo("JambTest1025");
        studentRepository.deleteByJambNo("JambTest1026");



    }

    @Test
    public void when_validJambNo_expect_findByJambNoAsNonNullUser() {

        //studentRepository.deleteById(2L);

        //studentRepository.deleteByJambNo("JambTest1001");

        Date date = new Date();
        Student student = new Student();
        student.setJambNo("JambTest1001");
        student.setEmail("randomemail1001@gmail.com");
        student.setPassword("JambTest1001");
        student.setDepartment("Computer Science");
        student.setDate_Created(date);
        student.setLevel("300");
        student.setDept_id(2L);
        studentRepository.save(student);

        Student dbStudent = studentRepository.findByJambNo("JambTest1001");

        //Assert.assertNotNull(dbStudent);
        Assert.assertEquals("JambTest1001",dbStudent.getJambNo());

    }

    @Test
    void findPersonByEmail() {

            Date date = new Date();
            Student student = new Student();
            student.setJambNo("JambTest1001");
            student.setEmail("randomemail1001@gmail.com");
            student.setPassword("JambTest1001");
            student.setDepartment("Computer Science");
            student.setDate_Created(date);
            student.setStudentId(2L);
            student.setLevel("300");
            student.setDept_id(2L);
            studentRepository.save(student);

            Optional<Student> dbStudent = studentRepository.findPersonByEmail("randomemail1001@gmail.com");

            Assert.assertNotNull(dbStudent);
            Assert.assertEquals("JambTest1001",dbStudent.get().getJambNo());
            Assert.assertEquals("randomemail1001@gmail.com",dbStudent.get().getEmail());
    }

    @Test
    void findByJambNo() {
        Date date = new Date();
        Student student = new Student();
        student.setJambNo("JambTest1001");
        student.setEmail("randomemail1001@gmail.com");
        student.setPassword("JambTest1001");
        student.setDepartment("Computer Science");
        student.setDate_Created(date);
        student.setStudentId(2L);
        student.setLevel("300");
        student.setDept_id(2L);
        studentRepository.save(student);

        Optional<Student> dbStudent = studentRepository.findPersonByEmail("randomemail1001@gmail.com");

        Assert.assertNotNull(dbStudent);
        Assert.assertEquals("JambTest1001",dbStudent.get().getJambNo());
        Assert.assertEquals("randomemail1001@gmail.com",dbStudent.get().getEmail());
    }



    @Test
    void findByLevel() throws ParseException {
        Date dt = new Date(); //saved to db
        SimpleDateFormat dateFormat;
        dateFormat = new SimpleDateFormat("yyyy-M-dd hh:mm:ss");

        String date_change = dateFormat.format(dt);
        System.out.println("Date as a string : " + date_change);


        Date date1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(date_change); //fetch from db

//        String date = dateFormat.format(date_change);
        System.out.println("Date as a date " + date1);




//        DateTimeFormatter.ofPattern("yyyy-M-dd hh:mm:ss").format(dt.toInstant());
//        LocalDateTime date = LocalDateTime.parse((CharSequence) dt,DateTimeFormatter.ofPattern("yyyy-M-dd hh:mm:ss"));
//        Instant instant = date.toInstant(ZoneOffset.UTC);
//        Date date1 = Date.from(instant);




        //studentRepository.deleteByJambNo("JambTest1023");
        //studentRepository.deleteByJambNo("JambTest1024");
        //studentRepository.deleteByJambNo("JambTest1025");
        //studentRepository.deleteByJambNo("JambTest1026");



        Student student1 = new Student();
        student1.setLevel("800");student1.setDepartment("Computer Science");student1.setJambNo("JambTest1023");student1.setPassword("JambTest1023");student1.setDate_Created(dt);student1.setDept_id(2L);student1.setEmail("randomemail1023@gmail.com");
        Student student2 = new Student();
        student2.setLevel("700");student2.setDepartment("Computer Science");student2.setJambNo("JambTest1024");student2.setPassword("JambTest1024");student2.setDate_Created(dt);student2.setDept_id(2L);student2.setEmail("randomemail1024@gmail.com");
        Student student3 = new Student();
        student3.setLevel("800");student3.setDepartment("Computer Science");student3.setJambNo("JambTest1025");student3.setPassword("JambTest1025");student3.setDate_Created(dt);student3.setDept_id(2L);student3.setEmail("randomemail1025@gmail.com");
        Student student4 = new Student();
        student4.setLevel("700");student4.setDepartment("Computer Science");student4.setJambNo("JambTest1026");student4.setPassword("JambTest1026");student4.setDate_Created(dt);student4.setDept_id(2L);student4.setEmail("randomemail1026@gmail.com");



        studentRepository.save(student1);
        studentRepository.save(student2);
        studentRepository.save(student3);
        studentRepository.save(student4);

        logger.info("student4 is " + student4);



        List<Student> listOf800Students = studentRepository.findByLevel("800");
        List<Student> listOf700Students = studentRepository.findByLevel("700");

        List <Student> student_800 = Arrays.asList(student1, student3);
        List <Student> student_700 = Arrays.asList(student2, student4);

        System.out.println("Date: "+dateFormat.format(dt));
        //listOf300Students.get()
//        Assert.assertEquals(dateFormat.format(listOf800Students),student_300);
        //Assert.assertEquals(listOf700Students,student_700);
        Assert.assertArrayEquals(listOf700Students.stream().map(s-> s.getJambNo()).toArray(), student_700.stream().map(s-> s.getJambNo()).toArray());
        Assert.assertArrayEquals(listOf800Students.stream().map(s-> s.getJambNo()).toArray(), student_800.stream().map(s-> s.getJambNo()).toArray());


    }

//    @AfterTest
//    void restore(){
//        studentRepository.deleteByJambNo("JambTest1023");
//        studentRepository.deleteByJambNo("JambTest1024");
//        studentRepository.deleteByJambNo("JambTest1025");
//        studentRepository.deleteByJambNo("JambTest1026");
//    }

    @Test
    void findAll() {


    }

    @Test
    void findByDepartment() {
        Date date = new Date();

        Student student1 = new Student();
        student1.setLevel("800");student1.setDepartment("LINGUISTICS");student1.setJambNo("JambTest1023");student1.setPassword("JambTest1023");student1.setDate_Created(date);student1.setDept_id(4L);student1.setEmail("randomemail1023@gmail.com");
        Student student2 = new Student();
        student2.setLevel("700");student2.setDepartment("LINGUISTICS");student2.setJambNo("JambTest1024");student2.setPassword("JambTest1024");student2.setDate_Created(date);student2.setDept_id(4L);student2.setEmail("randomemail1024@gmail.com");
        Student student3 = new Student();
        student3.setLevel("800");student3.setDepartment("LINGUISTICS");student3.setJambNo("JambTest1025");student3.setPassword("JambTest1025");student3.setDate_Created(date);student3.setDept_id(4L);student3.setEmail("randomemail1025@gmail.com");
        Student student4 = new Student();
        student4.setLevel("700");student4.setDepartment("LINGUISTICS");student4.setJambNo("JambTest1026");student4.setPassword("JambTest1026");student4.setDate_Created(date);student4.setDept_id(4L);student4.setEmail("randomemail1026@gmail.com");



        studentRepository.save(student1);
        studentRepository.save(student2);
        studentRepository.save(student3);
        studentRepository.save(student4);

        List<Student> student_repo = studentRepository.findByDepartment("LINGUISTICS");

        List <Student> student_dept = Arrays.asList(student1,student2, student3,student4);
        logger.info(" student from repo : " + student_repo);
        logger.info(" Student from Test : " + student_dept);


        Assert.assertArrayEquals(student_repo.stream().map(s-> s.getJambNo()).toArray(), student_dept.stream().map(s-> s.getJambNo()).toArray());
    }

    @Test
    void count() {
        long count_Repo = studentRepository.count();
        long count_Test = 12L;
        Assert.assertEquals(count_Test,count_Repo);
    }

    @Test
    void findByDepartmentAndStudent() {
        List<Object> student_Test = studentRepository.findByDepartmentAndStudent();
        ArrayList<String> test_list = new ArrayList<>();
        ArrayList<String> new_list = new ArrayList<>();


        for (int i = 0; i<student_Test.size();i++){
            Object[] object = (Object[]) student_Test.get(i);
            test_list.add(Arrays.toString(object));
        }
        Assert.assertEquals(true,!(test_list.isEmpty()));




    }

    @Test
    void findLevelByDepartmentAndStudent() {
        List deptList = studentRepository.findLevelByDepartmentAndStudent("Computer Science");
        ArrayList<String> test_list = new ArrayList<>();
        ArrayList<String> new_list = new ArrayList<>();

        for (int i = 0; i<deptList.size();i++){
            Object[] object = (Object[]) deptList.get(i);
            test_list.add(Arrays.toString(object));


        }
        Assert.assertEquals(true,!(test_list.isEmpty()));
    }

    @Test
    void findOldStudentByGivenDate() {
        long newStudent = studentRepository.findNewStudentByGivenDate();
        logger.info("List of students before date: " + newStudent);
        long count_Test = 8L;
        Assert.assertEquals(count_Test,newStudent);
    }

    @Test
    void findNewStudentByGivenDate() {
        long oldStudent = studentRepository.findNewStudentByGivenDate();
        logger.info("List of students after date : " + oldStudent);
        long count_test = 8L;
        Assert.assertEquals(count_test,oldStudent);

    }

//    public static class Scam {
//        private String deptName;
//    }
}