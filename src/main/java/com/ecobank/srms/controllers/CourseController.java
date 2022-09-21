package com.ecobank.srms.controllers;

import com.ecobank.srms.Service.CourseManageService;
import com.ecobank.srms.dto.CourseRegisterRequest;
import com.ecobank.srms.dto.CoursesDisplayRequest;
import com.ecobank.srms.dto.ViewCoursesRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("api/v1/student")
@AllArgsConstructor
@CrossOrigin
public class CourseController {
//    @Autowired
    private final CourseManageService courseManageService;



    @PostMapping(value="/register_course")
    public ResponseEntity <?> saveCoursePerStudent(@RequestBody CourseRegisterRequest courseRegisterRequest) throws IOException {
        //return ResponseEntity.ok().body(studentService.Register(studentRequest));
        return ResponseEntity.ok(courseManageService.saveCoursePerStudent(courseRegisterRequest));
    }

    @PostMapping(value="/view_course")
    public ResponseEntity view(@RequestBody ViewCoursesRequest viewCoursesRequest) throws Exception {
        //return ResponseEntity.ok().body(studentService.Register(studentRequest));
        return ResponseEntity.ok(courseManageService.view(viewCoursesRequest));
    }

    @PostMapping(value="/view_All_course")
    public ResponseEntity getCoursebyDepartment(@RequestBody CoursesDisplayRequest CourseDisplayRequest) throws Exception {
        //return ResponseEntity.ok().body(studentService.Register(studentRequest));
        return ResponseEntity.ok(courseManageService.getCoursebyDepartment(CourseDisplayRequest));
    }


}
