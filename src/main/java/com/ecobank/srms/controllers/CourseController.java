package com.ecobank.srms.controllers;

import com.ecobank.srms.Service.CourseManageService;
import com.ecobank.srms.Service.CourseManageServiceImpl;
import com.ecobank.srms.dto.CourseRegisterRequest;
import com.ecobank.srms.dto.CoursesDisplayRequest;
import com.ecobank.srms.dto.StudentDeleteCourseRequest;
import com.ecobank.srms.dto.ViewCoursesRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.logging.Logger;

@RestController
@RequestMapping("api/v1/student")
@AllArgsConstructor
@CrossOrigin
public class CourseController {
//    @Autowired
    private final CourseManageService courseManageService;


    final Logger  logger = Logger.getLogger(CourseController.class.getName());



    @PostMapping(value="/register_course")
    public ResponseEntity <?> saveCoursePerStudent(@Valid @RequestBody CourseRegisterRequest courseRegisterRequest) throws IOException {
        //return ResponseEntity.ok().body(studentService.Register(studentRequest));

        return ResponseEntity.status(HttpStatus.CREATED).body(courseManageService.saveCoursePerStudent(courseRegisterRequest));
    }

//    @PostMapping(value="/view_course")
//    public ResponseEntity view(@Valid @RequestBody ViewCoursesRequest viewCoursesRequest) throws Exception {
//        //return ResponseEntity.ok().body(studentService.Register(studentRequest));
//        return ResponseEntity.ok(courseManageService.view(viewCoursesRequest));
//    }

    @GetMapping(value="/view_course/{regNo}")
    public ResponseEntity view(@PathVariable String regNo) throws Exception {
        //return ResponseEntity.ok().body(studentService.Register(studentRequest));
        return ResponseEntity.ok(courseManageService.view(regNo));
    }

//    @PostMapping(value="/view_All_course")
//    public ResponseEntity getCoursebyDepartment(@Valid @RequestBody CoursesDisplayRequest CourseDisplayRequest) throws Exception {
//        //return ResponseEntity.ok().body(studentService.Register(studentRequest));
//        return ResponseEntity.ok(courseManageService.getCoursebyDepartment(CourseDisplayRequest));
//    }

    @GetMapping(value="/view_All_course/{department_name}")
    public ResponseEntity getCoursebyDepartment(@PathVariable String department_name) throws Exception {
        //return ResponseEntity.ok().body(studentService.Register(studentRequest));
        return ResponseEntity.ok(courseManageService.getCoursebyDepartment(department_name));
    }

    @DeleteMapping(value="/delete_course")
    public ResponseEntity deleteCourse(@Valid @RequestBody StudentDeleteCourseRequest studentDeleteCourseRequest) throws Exception {
        //return ResponseEntity.ok().body(studentService.Register(studentRequest));
        logger.info("This is Present_1: "+studentDeleteCourseRequest);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(courseManageService.studDelete(studentDeleteCourseRequest));
    }


}
