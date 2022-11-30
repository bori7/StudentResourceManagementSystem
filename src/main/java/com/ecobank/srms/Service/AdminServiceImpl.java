package com.ecobank.srms.Service;

import com.ecobank.srms.dto.*;
import com.ecobank.srms.model.*;
import com.ecobank.srms.repository.*;
import com.ecobank.srms.utils.Token;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;


@Data
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    HttpServletRequest httpServletRequest;

    @Autowired
    StudentServiceImpl studentService;

    @Autowired
    DepartmentService departmentService;

    @Autowired
    CourseManageServiceImpl courseManageService;

    @Autowired
    IdVerificationRepository idVerificationRepository;

    Logger logger = Logger.getLogger(AdminServiceImpl.class.getName());

    @Override
    public AdminRegisterResponse register(AdminRegisterRequest adminRegisterRequest) throws IOException {

        boolean isPresent_verify = idVerificationRepository.findByuserId(adminRegisterRequest.getUsername()).isPresent();

        boolean isPresentUsername = adminRepository.findByUsername(adminRegisterRequest.getUsername()).isPresent();

        if(!(isPresent_verify))
            return AdminRegisterResponse.builder().message("Access Not Granted, Contact Support").build();


        if (isPresentUsername) {
            return AdminRegisterResponse.builder().message("This Username Already Exists").build();
        } else {
            String password = adminRegisterRequest.getPassword();
            String confirmPassword = adminRegisterRequest.getConfirmPassword();
            if (!(password.equals(confirmPassword))) {
                return AdminRegisterResponse.builder().message("Password must match confirm Password").build();
            } else {
                Admin admin = new Admin();
                ModelMapper modelMapper = new ModelMapper();
                BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
                final String encodedPassword = bcryptPasswordEncoder.encode(adminRegisterRequest.getPassword());
                adminRegisterRequest.setPassword(encodedPassword);
                Date date = new Date();
                adminRegisterRequest.setDate_Created(date);
                modelMapper.map(adminRegisterRequest, admin);
                adminRepository.save(admin);
            }
        }

        return AdminRegisterResponse.builder().message("Admin has registered")
                .username(adminRegisterRequest.getUsername())
                .fName(adminRegisterRequest.getFName())
                .build();
    }

    @Override
    public AdminLoginResponse login(AdminLoginRequest adminLoginRequest) throws IOException {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        boolean isPresent = adminRepository.findByUsername(adminLoginRequest.getUsername()).isPresent();
        Admin admin = adminRepository.findByUsername(adminLoginRequest.getUsername()).get();

        Token token = new Token();

        if (!(isPresent)) {
            return AdminLoginResponse.builder().message("The User Doesn't exist").build();

        } else {
            if (!passwordEncoder.matches(adminLoginRequest.getPassword(), admin.getPassword())) {
                return AdminLoginResponse.builder().message("Incorrect Password").build();
            }
        }
        token = studentService.extractToken(httpServletRequest);

        return AdminLoginResponse.builder().message("Login Successful")
                .username(adminLoginRequest.getUsername())
                .token(String.valueOf(token.getAccessToken()))
                .build();

    }

    @Override
    public ResetPasswordResponse reset(AdminResetPasswordRequest adminResetPasswordRequest) throws IOException {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Admin currentAdmin = adminRepository.findByUsername(adminResetPasswordRequest.getUsername()).get();
        String newPassword = adminResetPasswordRequest.getNewPassword();
        String confirmPassword = adminResetPasswordRequest.getConfirmPassword();


        if (currentAdmin == null) {
            return new ResetPasswordResponse("Please Register, User does not exist");
        } else {
            if (passwordEncoder.matches(newPassword, currentAdmin.getPassword())) {
                return ResetPasswordResponse.builder().message("Old password cannot be the same as new password").build();
            } else {
                if (confirmPassword.equals(newPassword)) {
                    currentAdmin.setPassword(passwordEncoder.encode(newPassword));
                    adminRepository.save(currentAdmin);
                    return ResetPasswordResponse.builder().message("Password successfully Reset").build();
                } else {
                    return ResetPasswordResponse.builder().message("password must match").build();
                }
            }
        }
    }

    @Override
    public AdminCreateCourseResponse create(AdminCreateCourseRequest adminCreateCourseRequest) throws IOException {
        //adminCreateCourseRequest.setDepartmentname(adminCreateCourseRequest.getDepartmentname().toUpperCase());

        Courses courses_name = courseRepository.findAllByNameOfCourse(adminCreateCourseRequest.getNameOfCourse());
        Department department = departmentRepository.findByDeptName(adminCreateCourseRequest.getDepartmentname());


        if (!(courses_name == null)) {
            return AdminCreateCourseResponse.builder().message("This course exists already").build();
        }

        if(department==null){
            return AdminCreateCourseResponse.builder().message("This department does not exist").build();
        }



        else {
            Courses courses = new Courses();
            ModelMapper modelMapper = new ModelMapper();
            adminCreateCourseRequest.setDepartmentname(adminCreateCourseRequest.getDepartmentname().toUpperCase());
            modelMapper.map(adminCreateCourseRequest, courses);
            courseRepository.save(courses);
            return AdminCreateCourseResponse.builder()
                    .message("Course has been created")
                    .course_code(adminCreateCourseRequest.getCourse_code())
                    .course_Descr(adminCreateCourseRequest.getCourse_Descr())
                    .len_course(adminCreateCourseRequest.getLen_course())
                    .departmentname(adminCreateCourseRequest.getDepartmentname())
                    .nameOfCourse(adminCreateCourseRequest.getNameOfCourse())
                    .unit_of_course(adminCreateCourseRequest.getUnit_of_course())
                    .status_course(adminCreateCourseRequest.getStatus_course())
                    .build();

        }
    }

    @Override
    public AdminCreateDepartmentResponse createDept(AdminCreateDepartmentRequest adminCreateDepartmentRequest) {
        Department department_name = departmentRepository.findByDeptName(adminCreateDepartmentRequest.getDeptName());

        if (!(department_name == null)) {
            return AdminCreateDepartmentResponse.builder().message("Department Already Exists").build();
        } else {
            Department department = new Department();
            ModelMapper modelMapper = new ModelMapper();
            modelMapper.map(adminCreateDepartmentRequest, department);
            departmentRepository.save(department);
            return AdminCreateDepartmentResponse.builder()
                    .deptName(adminCreateDepartmentRequest.getDeptName())
                    .message("Department has been created")
                    .build();

        }

    }

    @Override
    public AdminCreateStudentResponse createStud(AdminCreateStudentRequest adminCreateStudentRequest) {
        Student student_jambNo = studentRepository.findByJambNo(adminCreateStudentRequest.getJambNo());
        boolean isPresent_email = studentRepository.findPersonByEmail(adminCreateStudentRequest.getEmail()).isPresent();
        Department department = departmentRepository.findByDeptName(adminCreateStudentRequest.getDepartment());

        logger.info("jambNo"+adminCreateStudentRequest.getJambNo());
        logger.info("Department"+adminCreateStudentRequest.getDepartment());
        logger.info("level"+adminCreateStudentRequest.getLevel());
        logger.info("password"+adminCreateStudentRequest.getPassword());
        logger.info("Email"+adminCreateStudentRequest.getEmail());
        logger.info("dept id"+adminCreateStudentRequest.getDept_id());

        if (!(student_jambNo==null)){
            return AdminCreateStudentResponse.builder().message("Student already Exists").build();
        }

        if(isPresent_email){
            return AdminCreateStudentResponse.builder().message("Email Already Exists").build();
        }

        if (department==null){
            return AdminCreateStudentResponse.builder().message("Department Does not Exist").build();
        }

        logger.info("jambNo"+adminCreateStudentRequest.getJambNo());
        logger.info("Department"+adminCreateStudentRequest.getDepartment());
        logger.info("level"+adminCreateStudentRequest.getLevel());
        logger.info("password"+adminCreateStudentRequest.getPassword());
        logger.info("Email"+adminCreateStudentRequest.getEmail());
        logger.info(""+adminCreateStudentRequest.getDept_id());
        Student student = new Student();
        ModelMapper modelMapper = new ModelMapper();
        BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
        final String encodedPassword = bcryptPasswordEncoder.encode(adminCreateStudentRequest.getPassword());
        adminCreateStudentRequest.setPassword(encodedPassword);
        Long Dept_Id = departmentService.getDeptId(adminCreateStudentRequest.getDepartment());
        Date date = new Date();
        adminCreateStudentRequest.setDate_Created(date);
        adminCreateStudentRequest.setDept_id(Dept_Id);
        modelMapper.map(adminCreateStudentRequest, student);
        studentRepository.save(student);
        return AdminCreateStudentResponse.builder().message("New Student Created")
                .jambNo(adminCreateStudentRequest.getJambNo())
                .level(adminCreateStudentRequest.getLevel())
                .department(adminCreateStudentRequest.getDepartment().toUpperCase())
                .email(adminCreateStudentRequest.getEmail())
                .dept_id(adminCreateStudentRequest.getDept_id())
                .date_Created(adminCreateStudentRequest.getDate_Created())
                .build();

    }

    @Override
    public Object displayStudDept(AdminFindStudentRequest adminFindStudentRequest) {
        Department department = departmentRepository.findByDeptName(adminFindStudentRequest.getDeptName());
        List <Student> student = studentRepository.findByDepartment(adminFindStudentRequest.getDeptName());

        if(department==null){
            return "There is no department with that Name";
        }

        List<Object> studView = new ArrayList<>();

        if (student.isEmpty()) {
            return "There is no student under the department";
        }

        else{
            for (int i = 0; i < student.size(); i++){
                studView.add(student.get(i));
            }
            return studView;
        }
    }

    @Override
    public AdminFindStudentLevelResponse displayStudLevel(AdminFindStudentLevelRequest adminFindStudentLevelRequest) {
        List <Student> student = studentRepository.findByLevel(adminFindStudentLevelRequest.getLevel());
        List<Object> studView = new ArrayList<>();
        if (student.isEmpty()) {
            return AdminFindStudentLevelResponse.builder()
                    .message("There are no students with that Level")
                    .build();
        }
        else{
            for (int i = 0; i < student.size(); i++){
                ViewStudent viewStudent = new ViewStudent();
                viewStudent.setJambNo(student.get(i).getJambNo());
                viewStudent.setLevel(student.get(i).getLevel());
                viewStudent.setDepartment(student.get(i).getDepartment());
                viewStudent.setDate_Created(student.get(i).getDate_Created());
                viewStudent.setEmail(student.get(i).getEmail());
                studView.add(viewStudent);
            }
            return AdminFindStudentLevelResponse.builder()
                    .response("There are the students with Level :" + adminFindStudentLevelRequest.getLevel())
                    .message("Successful")
                    .data(studView)
                    .build();
        }

    }

    @Override
    public AdminChangePasswordResponse changePassword(AdminChangePasswordRequest adminChangePasswordRequest) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        boolean iscurrentAdmin = adminRepository.findByUsername(adminChangePasswordRequest.getUsername()).isPresent();

        String currentPassword = adminChangePasswordRequest.getCurrentPassword();
        String newPassword = adminChangePasswordRequest.getNewPassword();
        String confirmPassword = adminChangePasswordRequest.getConfirmPassword();

        if (!(iscurrentAdmin)) {
            return AdminChangePasswordResponse.builder()
                    .message(adminChangePasswordRequest.getUsername() + " Does not exist, please register ")
                    .code("99")
                    .build();
        } else {
            Admin currentAdmin = adminRepository.findByUsername(adminChangePasswordRequest.getUsername()).get();
            if (passwordEncoder.matches(newPassword, currentAdmin.getPassword())) {
                return AdminChangePasswordResponse.builder()
                        .code("99")
                        .message("Old password cannot be the same as new password")
                        .build();
            }

            if(!(passwordEncoder.matches(currentPassword,currentAdmin.getPassword()))){
                return AdminChangePasswordResponse.builder()
                        .code("99")
                        .message("password is incorrect, please enter original password")
                        .build();
            }



            else {
                if (confirmPassword.equals(newPassword)) {
                    currentAdmin.setPassword(passwordEncoder.encode(newPassword));
                    adminRepository.save(currentAdmin);
                    return AdminChangePasswordResponse.builder()
                            .code("00")
                            .message("Password successfully changed")
                            .username(adminChangePasswordRequest.getUsername())
                            .build();
                } else {
                    return AdminChangePasswordResponse.builder()
                            .message("Password must match")
                            .code("99")
                            .build();
                }
            }
        }
    }
}

