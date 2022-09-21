package com.ecobank.srms.Service;

import com.ecobank.srms.dto.*;
import com.ecobank.srms.model.Courses;

import java.util.List;

public interface CourseManageService {
    String getCourseName (Long Id);

    List<CourseManageResponse> saveCoursePerStudent(CourseRegisterRequest courseRegisterRequest);

    Object view(ViewCoursesRequest viewCoursesRequest) throws Exception;

    Courses getAll (String courseName);

    Object getCoursebyDepartment(CoursesDisplayRequest CoursesDisplayRequest);
}
