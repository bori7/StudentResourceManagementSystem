package com.ecobank.srms.Service;

import com.ecobank.srms.dto.*;
import com.ecobank.srms.model.CourseManage;
import com.ecobank.srms.model.Courses;
import com.ecobank.srms.model.ViewCourse;
import com.ecobank.srms.repository.CourseManageRepository;
import com.ecobank.srms.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


@Service
public class CourseManageServiceImpl implements CourseManageService {
    @Autowired
    private CourseManageRepository courseManageRepository;

    //    @Autowired
//    private CourseManage courseManage;
    @Autowired
    private CourseRepository courseRepository;

    Logger logger = Logger.getLogger(CourseManageServiceImpl.class.getName());


    @Override
    public String getCourseName(Long Id) {
        String courseName = "";
        Courses courses = courseRepository.findByCourseId(Id);
        if (courses == null) {
            logger.info("The course doesnt exist");
        } else {
            courseName = courses.getNameOfCourse();
        }
        return courseName;
    }

    CourseManageResponse courseManageResponse = new CourseManageResponse();

    @Override
    @Transactional
    public List<CourseManageResponse> saveCoursePerStudent(CourseRegisterRequest courseRegisterRequest) {
        List<CourseManageResponse> courseManageResponses = new ArrayList<>();
        int noOfCourses = courseRegisterRequest.getCourses().size();
        for (int i = 0; i < noOfCourses; i++) {
            CourseManage courseManage = new CourseManage();
            courseManage.setStudReg(courseRegisterRequest.getJambNo());
            courseManage.setCourse_Id(courseRegisterRequest.getCourses().get(i));
            courseManage.setStudReg(courseRegisterRequest.getJambNo());
            courseManage.setCourse_Name(getCourseName(courseRegisterRequest.getCourses().get(i)));
            courseManageRepository.save(courseManage);
            courseManageResponse.setResp_code("00");
            courseManageResponse.setResp_msg("Course Saved");

            courseManageResponses.add(courseManageResponse);
        }

        return courseManageResponses;

    }

    @Override
    public Object view(ViewCoursesRequest viewCoursesRequest) throws Exception {
        List<CourseManage> courseManage = courseManageRepository.findByStudReg(viewCoursesRequest.getRegNo());
        List<ViewCourse> viewCoursesResponses = new ArrayList<>();
        if (courseManage == null) {
            return "The student has not registered for a course";

        } else {
            for (int i = 0; i < courseManage.size(); i++) {
                ViewCourse viewCourse = new ViewCourse();
                Courses courses = courseRepository.findByCourseId(courseManage.get(i).getCourse_Id());

                viewCourse.setCourseId(courses.getCourseId());
                viewCourse.setCourseCode(courses.getCourse_code());
                viewCourse.setCourseName(courses.getNameOfCourse());
                viewCourse.setStatus(courses.getStatus_course());
                viewCourse.setUnit(courses.getUnit_of_course());
                viewCoursesResponses.add(viewCourse);
            }
            return viewCoursesResponses;
        }
    }

    @Override
    public Courses getAll(String courseName) {
        Courses course = new Courses();
        Courses course1 = courseRepository.findAllByNameOfCourse(courseName);
        if (course1 == null) {
            logger.info("The course doesnt exist");
        } else {
            course = course1;
        }
        return course;
    }

    @Override
    public Object getCoursebyDepartment(CoursesDisplayRequest CoursesDisplayRequest) {
        List<Courses> courses = courseRepository.findAllBydepartmentname(CoursesDisplayRequest.getDepartment_name());
        List<ViewCourse> CoursesDisplayResponse = new ArrayList<>();
        //boolean ans = courses.isEmpty();
        if (courses == null) {
            return "The department Does not exist";
        }
//        if(courses == ){
//            return "The Department does not have any courses available";
//        }
        else {
            for (int i = 0; i < courses.size(); i++) {
                ViewCourse viewCourse = new ViewCourse();
                Courses courses1 = courseRepository.findByCourseId(courses.get(i).getCourseId());

                viewCourse.setCourseId(courses1.getCourseId());
                viewCourse.setCourseCode(courses1.getCourse_code());
                viewCourse.setCourseName(courses1.getNameOfCourse());
                viewCourse.setStatus(courses1.getStatus_course());
                viewCourse.setUnit(courses1.getUnit_of_course());
                CoursesDisplayResponse.add(viewCourse);
            }

            return CoursesDisplayResponse;
        }


    }

//     public Object deleteCourse (Long Id){
//        CourseManage courseManage = new CourseManage();
//         boolean Ispresent = courseManageRepository.findById(Id).isPresent();
//         if (!(Ispresent)){
//             logger.info("Course does not exist");
//         }
//         else {
//             courseManageRepository.deleteById(Id);
//         }
//        return courseManage;
//    }

    @Override
    public StudentDeleteCourseResponse studDelete(StudentDeleteCourseRequest studentDeleteCourseRequest) {

        List<CourseManage> courseManage1 = courseManageRepository.findByStudReg(studentDeleteCourseRequest.getJambNo());
        if (courseManage1 == null) {
            return StudentDeleteCourseResponse.builder().message("The Student hasnt registered a course").build();
        }
        else{
            for (int i = 0; i < courseManage1.size(); i++){
                CourseManage coursemanage = new CourseManage();
                courseManageRepository.deleteById(studentDeleteCourseRequest.getCourse_Id());
                courseManageRepository.save(coursemanage);

            }
        }
        return StudentDeleteCourseResponse.builder().message("Course Deleted").build();

    }


}


