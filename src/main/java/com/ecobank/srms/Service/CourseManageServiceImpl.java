package com.ecobank.srms.Service;

import com.ecobank.srms.dto.*;
import com.ecobank.srms.model.CourseManage;
import com.ecobank.srms.model.Courses;
import com.ecobank.srms.model.Department;
import com.ecobank.srms.model.ViewCourse;
import com.ecobank.srms.repository.CourseManageRepository;
import com.ecobank.srms.repository.CourseRepository;
import com.ecobank.srms.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
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

    @Autowired
    private DepartmentRepository departmentRepository;

    final Logger logger = Logger.getLogger(CourseManageServiceImpl.class.getName());


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



    @Override
    @Transactional
    public List<CourseManageResponse> saveCoursePerStudent(CourseRegisterRequest courseRegisterRequest) {
        List<CourseManage> findRegNo= courseManageRepository.findByStudReg(courseRegisterRequest.getJambNo());
        logger.info(findRegNo.toString());
        List<CourseManageResponse> courseManageResponses = new ArrayList<>();
        int noOfCourses = courseRegisterRequest.getCourses().size();
        CourseManage courseManage ;
        CourseManageResponse courseManageResponse ;
//        if(findRegNo.isEmpty()){
            logger.info("Get her for non-existing student");
            for (int i = 0; i < noOfCourses; i++) {
                 courseManage = new CourseManage();
                 courseManageResponse = new CourseManageResponse();
                List<CourseManage> existingCourses = courseManageRepository.findByCourseIdAndStudReg(courseRegisterRequest.getCourses().get(i), courseRegisterRequest.getJambNo());
                courseManage.setStudReg(courseRegisterRequest.getJambNo());
                courseManage.setCourse_Id(courseRegisterRequest.getCourses().get(i));

                courseManage.setCourse_Name(getCourseName(courseRegisterRequest.getCourses().get(i)));

                logger.info("Course ID: "+  courseRegisterRequest.getCourses().get(i));

                if (existingCourses.isEmpty()){
                    courseManageRepository.save(courseManage);
                    courseManageResponse.setResp_code("00");
                    courseManageResponse.setResp_msg("Course Saved");
                }else{
                    courseManageResponse.setResp_code("99");
                    courseManageResponse.setResp_msg("Course " + getCourseName(courseRegisterRequest.getCourses().get(i)) + " has already been registered");
                }

                courseManageResponse.setData(courseManage);
                courseManageResponses.add(courseManageResponse);
                logger.info("Course Manage Resp: "+  courseManageResponses);

            }
//        }
//        else{
//            logger.info("Get her for existing student");
//        for (int i = 0; i < noOfCourses; i++) {
//            CourseManage courseManage = new CourseManage();
//            courseManage.setCourseManageId(courseManage.getCourseManageId());
//            courseManage.setStudReg(courseRegisterRequest.getJambNo());
//            courseManage.setCourse_Id(courseRegisterRequest.getCourses().get(i));
//            courseManage.setCourse_Name(getCourseName(courseRegisterRequest.getCourses().get(i))); courseManageRepository.save(courseManage);
//
//            logger.info("I am currently in this here " + courseManage);
//                if(findRegNo.get(i).equals(courseManage)){
//                    logger.info("I am currently here"+courseManage);
//                courseManageResponse.setResp_code("99");
//                courseManageResponse.setResp_msg("Course " + courseRegisterRequest.getCourses().get(i) + "Has already been registered");
//                courseManageResponses.add(courseManageResponse);
//                    continue;
//            } else {
//                logger.info("Hello");
//                courseManageResponse.setResp_code("00");
//                courseManageResponse.setResp_msg("Course Saved");
//                courseManageResponse.setData(courseManage);
//                courseManageResponses.add(courseManageResponse);
//
//            }}


//        }


        return courseManageResponses;

    }

    @Override
    public Object view(ViewCoursesRequest viewCoursesRequest) throws Exception {
        List<CourseManage> courseManage = courseManageRepository.findByStudReg(viewCoursesRequest.getRegNo());
        List<ViewCourse> viewCoursesResponses = new ArrayList<>();
        if (courseManage == null || courseManage.isEmpty()) {
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
    public Object getCoursebyDepartment(CoursesDisplayRequest coursesDisplayRequest) {
        Department department = departmentRepository.findByDeptName(coursesDisplayRequest.getDepartment_name());

        List<Courses> courses = courseRepository.findAllBydepartmentname(coursesDisplayRequest.getDepartment_name());
        List<ViewCourse> CoursesDisplayResponse = new ArrayList<>();
        //boolean ans = courses.isEmpty();
        if(department==null){
            return "The Department does not exist";

        }

        if (courses.isEmpty()) {
            return "The Department does not have any courses";
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



    @Override
    public StudentDeleteCourseResponse studDelete(StudentDeleteCourseRequest studentDeleteCourseRequest) {
        logger.info("studentDeleteCourseRequest:" + studentDeleteCourseRequest);
        List<CourseManage> findRegNoAndStud= courseManageRepository.findByCourseIdAndStudReg(studentDeleteCourseRequest.getCourse_Id(),studentDeleteCourseRequest.getJambNo());
        List<CourseManage> courseManage1 = courseManageRepository.findByStudReg(studentDeleteCourseRequest.getJambNo());

        logger.info("course_Manage : " + courseManage1);
        logger.info("regNO:" + findRegNoAndStud);

        if (courseManage1 == null || courseManage1.isEmpty()) {

            return StudentDeleteCourseResponse.builder().message("The Student hasn't registered for any course").build();
        }
        if (findRegNoAndStud == null || findRegNoAndStud.isEmpty()){
            return StudentDeleteCourseResponse.builder().message("The student hasn't registered for this course").build();
        }
        else{

//            for (int i = 0; i < courseManage1.size(); i++){
//
//                CourseManage coursemanage = new CourseManage();
                logger.info("Course ID: "+studentDeleteCourseRequest.getCourse_Id());
                logger.info("RegNo: "+studentDeleteCourseRequest.getJambNo());
                courseManageRepository.deleteBycourseId(studentDeleteCourseRequest.getCourse_Id(),studentDeleteCourseRequest.getJambNo());
//                courseManageRepository.save(coursemanage);

//            }

        return StudentDeleteCourseResponse.builder().message("Course Deleted").build();
        }
    }


}


