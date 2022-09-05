package com.ecobank.srms.Service;

import com.ecobank.srms.dto.*;
import com.ecobank.srms.model.CourseManage;
import com.ecobank.srms.model.Courses;
import com.ecobank.srms.model.ViewCourse;
import com.ecobank.srms.repository.CourseManageRepository;
import com.ecobank.srms.repository.CourseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


@Service
public class CourseManageServiceImpl implements CourseManageService{
    @Autowired
    private CourseManageRepository courseManageRepository;

//    @Autowired
//    private CourseManage courseManage;
    @Autowired
    private CourseRepository courseRepository;

    Logger logger = Logger.getLogger(CourseManageServiceImpl.class.getName());



    @Override
    public String getCourseName(Long Id) {
        String courseName="";
        Courses courses = courseRepository.findByCourseId(Id);
        if (courses == null) {
            logger.info("The course doesnt exist");
        } else {
            courseName = courses.getNameOfCourse();
        }
        return courseName;
    }
    CourseManageResponse courseManageResponse= new CourseManageResponse();
    @Override
    @Transactional
    public List<CourseManageResponse> saveCoursePerStudent(CourseRegisterRequest courseRegisterRequest) {
        List<CourseManageResponse> courseManageResponses= new ArrayList<>();
            int noOfCourses= courseRegisterRequest.getCourses().size();
            for(int i=0; i<noOfCourses; i++){
                CourseManage courseManage= new CourseManage();
                courseManage.setStudReg(courseRegisterRequest.getRegNo());
                courseManage.setCourse_Id(courseRegisterRequest.getCourses().get(i));
                courseManage.setStudReg(courseRegisterRequest.getRegNo());
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
        List<ViewCourse> viewCoursesResponses= new ArrayList<>();
        if(courseManage==null){
            return "The student has not registered for a course";

        }
        else {
            for (int i = 0; i < courseManage.size(); i++) {
                ViewCourse viewCourse = new ViewCourse();
                Courses courses = courseRepository.findByCourseId(courseManage.get(i).getCourse_Id());

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
        if(course1==null){
            logger.info("The course doesnt exist");
        }
        else {
            course = course1;
        }
        return course;
    }

}


