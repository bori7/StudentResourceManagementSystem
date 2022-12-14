package com.ecobank.srms.Service;

import com.ecobank.srms.dto.*;
import com.ecobank.srms.exceptions.GenericException;
import com.ecobank.srms.model.CourseManage;
import com.ecobank.srms.model.Courses;
import com.ecobank.srms.model.Department;
import com.ecobank.srms.model.ViewCourse;
import com.ecobank.srms.repository.CourseManageRepository;
import com.ecobank.srms.repository.CourseRepository;
import com.ecobank.srms.repository.DepartmentRepository;
import com.ecobank.srms.utils.ResponseCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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
            return "The course doesnt exist";
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
            logger.info("Get here for non-existing student");
            for (int i = 0; i < noOfCourses; i++) {
                 courseManage = new CourseManage();
                 courseManageResponse = new CourseManageResponse();
                List<CourseManage> existingCourses = courseManageRepository.findByCourseIdAndStudReg(courseRegisterRequest.getCourses().get(i), courseRegisterRequest.getJambNo());
                courseManage.setStudReg(courseRegisterRequest.getJambNo());
                courseManage.setCourse_Id(courseRegisterRequest.getCourses().get(i));
                courseManage.setCourse_Name(getCourseName(courseRegisterRequest.getCourses().get(i)));

               Courses courses =  courseRepository.findByCourseId(courseRegisterRequest.getCourses().get(i));
               if (courses == null)
               {
                   throw new GenericException(ResponseCodes.NOT_FOUND," course with ID: " + courseRegisterRequest.getCourses().get(i) + " Does not exist", HttpStatus.NOT_FOUND);

               }
                logger.info("Course ID: "+  courseRegisterRequest.getCourses().get(i));

                if (existingCourses.isEmpty()){
                    courseManageRepository.save(courseManage);
                    courseManageResponse.setResp_code("00");
                    courseManageResponse.setResp_msg("Course Saved");
                }

                else{
//                    throw new GenericException(ResponseCodes.ALREADY_EXIST, "Course " + getCourseName(courseRegisterRequest.getCourses().get(i)) + " has already been registered", HttpStatus.BAD_REQUEST);

                    courseManageResponse.setResp_code("99");
                    courseManageResponse.setResp_msg("Course " + getCourseName(courseRegisterRequest.getCourses().get(i)) + " has already been registered");
//
                }

                courseManageResponse.setData(courseManage);
                courseManageResponses.add(courseManageResponse);
                logger.info("Course Manage Resp: "+  courseManageResponses);

            }



        return courseManageResponses;

    }

    @Override
    public Object view(ViewCoursesRequest viewCoursesRequest) throws Exception {
        List<CourseManage> courseManage = courseManageRepository.findByStudReg(viewCoursesRequest.getRegNo());
        List<ViewCourse> viewCoursesResponses = new ArrayList<>();
        if (courseManage == null || courseManage.isEmpty()) {
            throw new GenericException(ResponseCodes.NOT_FOUND, "The student has not registered for a course", HttpStatus.NOT_FOUND);

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
    public Object view(String regNo) throws Exception {
        List<CourseManage> courseManage = courseManageRepository.findByStudReg(regNo);
        List<ViewCourse> viewCoursesResponses = new ArrayList<>();
        if (courseManage == null || courseManage.isEmpty()) {
            throw new GenericException(ResponseCodes.NOT_FOUND, "The student has not registered for a course", HttpStatus.NOT_FOUND);

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
        logger.info("coursesDisplayRequest: " + coursesDisplayRequest);
        logger.info("Department name: " + coursesDisplayRequest.getDepartment_name());

        List<Courses> courses = courseRepository.findAllBydepartmentname(coursesDisplayRequest.getDepartment_name().toUpperCase());
        List<ViewCourse> CoursesDisplayResponse = new ArrayList<>();
        //boolean ans = courses.isEmpty();
        if(department==null){
            throw new GenericException(ResponseCodes.NOT_FOUND, "The Department does not exist", HttpStatus.NOT_FOUND);

//            return "The Department does not exist";

        }

        if (courses.isEmpty()) {

            throw new GenericException(ResponseCodes.NOT_FOUND, "The Department does not have any courses", HttpStatus.NOT_FOUND);

//            return "The Department does not have any courses";
        }




        else {
            for (int i = 0; i < courses.size(); i++) {
                ViewCourse viewCourse = new ViewCourse();
                Courses courses1 = courseRepository.findByCourseId(courses.get(i).getCourseId());

                viewCourse.setCourseId(courses1.getCourseId());
                viewCourse.setCourseCode(courses1.getCourse_code());
                viewCourse.setCourseName(courses1.getNameOfCourse());
                viewCourse.setStatus(courses1.getStatus_course());
                viewCourse.setUnit(courses1.getUnit_of_course());
                viewCourse.setCourseDesc(courses1.getCourse_Descr());
                CoursesDisplayResponse.add(viewCourse);
            }

            return CoursesDisplayResponse;
        }


    }

    @Override
    public Object getCoursebyDepartment(String department_name) {
        Department department = departmentRepository.findByDeptName(department_name);
        logger.info("coursesDisplayRequest: " + department_name);
        logger.info("Department name: " + department_name);

        List<Courses> courses = courseRepository.findAllBydepartmentname(department_name.toUpperCase());
        List<ViewCourse> CoursesDisplayResponse = new ArrayList<>();
        //boolean ans = courses.isEmpty();
        if(department==null){
            throw new GenericException(ResponseCodes.NOT_FOUND, "The Department does not exist", HttpStatus.NOT_FOUND);

//            return "The Department does not exist";

        }

        if (courses.isEmpty()) {

            throw new GenericException(ResponseCodes.NOT_FOUND, "The Department does not have any courses", HttpStatus.NOT_FOUND);

//            return "The Department does not have any courses";
        }




        else {
            for (int i = 0; i < courses.size(); i++) {
                ViewCourse viewCourse = new ViewCourse();
                Courses courses1 = courseRepository.findByCourseId(courses.get(i).getCourseId());

                viewCourse.setCourseId(courses1.getCourseId());
                viewCourse.setCourseCode(courses1.getCourse_code());
                viewCourse.setCourseName(courses1.getNameOfCourse());
                viewCourse.setStatus(courses1.getStatus_course());
                viewCourse.setUnit(courses1.getUnit_of_course());
                viewCourse.setCourseDesc(courses1.getCourse_Descr());
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
            throw new GenericException(ResponseCodes.NOT_FOUND, "The Student hasn't registered for any course", HttpStatus.NOT_FOUND);

//            return StudentDeleteCourseResponse.builder().message("The Student hasn't registered for any course").build();
        }
        if (findRegNoAndStud == null || findRegNoAndStud.isEmpty()){
            throw new GenericException(ResponseCodes.NOT_FOUND, "The Student hasn't registered for any course", HttpStatus.NOT_FOUND);


//            return StudentDeleteCourseResponse.builder().message("The student hasn't registered for this course").build();
        }
        else{


                logger.info("Course ID: "+studentDeleteCourseRequest.getCourse_Id());
                logger.info("RegNo: "+studentDeleteCourseRequest.getJambNo());
                courseManageRepository.deleteBycourseId(studentDeleteCourseRequest.getCourse_Id(),studentDeleteCourseRequest.getJambNo());


        return StudentDeleteCourseResponse.builder().message("Course Deleted").build();
        }
    }


}


