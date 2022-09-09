package com.ecobank.srms.Service;

import com.ecobank.srms.dto.*;
import com.ecobank.srms.model.Student;
import com.ecobank.srms.repository.StudentRepository;
//import org.modelmapper.ModelMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.logging.Logger;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired

    private StudentRepository studentRepository;
    @Autowired
    private DepartmentServiceImpl departmentService;
    Logger logger = Logger.getLogger(StudentServiceImpl.class.getName());


    @Override
    public StudentResponse Register(StudentRequest studentRequest) throws IOException {
        boolean isPresent = studentRepository.findPersonByRegNo(studentRequest.getRegNo()).isPresent();
        //Student isPresent = studentRepository.findByUserName(studentRequest.getUserName());

        if ((isPresent)) {
            return StudentResponse.builder().message("This registration exists please sign in").build();
            //return "The Registration `number is existing, please sign in";
        } else {
            Student student = new Student();
            ModelMapper modelMapper = new ModelMapper();
            BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
            final String encodedPassword = bcryptPasswordEncoder.encode(studentRequest.getPassword());
            studentRequest.setPassword(encodedPassword);
            Long Dept_Id = departmentService.getDeptId(studentRequest.getDepartment());
            Date date = new Date();
            studentRequest.setDate_Created(date);
            studentRequest.setDept_Id(Dept_Id);
            logger.info("Registration No " + studentRequest.getRegNo());
            logger.info("Last Name " + studentRequest.getLastName());
            logger.info("First Name " + studentRequest.getFirstName());
            logger.info("Level " + studentRequest.getLevel());
            logger.info("Department" + studentRequest.getDepartment());
            logger.info("USerName " + studentRequest.getUserName());
            modelMapper.map(studentRequest, student);

            studentRepository.save(student);
            return StudentResponse.builder().message("Thank you for registering").build();
        }
    }

    @Override
    public StudentResponse Login(LoginRequest loginRequest) throws IOException {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Student student = new Student();
        student = studentRepository.findByUserName((loginRequest.getUserName()));

        if (student == null) {
            return StudentResponse.builder().message("The User Doesn't exist").build();

        } else {
            if (!passwordEncoder.matches(loginRequest.getPassword(), student.getPassword())) {
                return StudentResponse.builder().message("Incorrect Password").build();

            } else {
                return StudentResponse.builder().message("Login Successful").build();

            }
        }

    }

    @Override
    public ChangePasswordResponse updateCurrentPassword(ChangePasswordRequest changePasswordRequest) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Student currentStudent = studentRepository.findByUserName(changePasswordRequest.getUserName());
        String newPassword = changePasswordRequest.getNewPassword();
        String confirmPassword = changePasswordRequest.getConfirmPassword();

        if (currentStudent == null) {
            return new ChangePasswordResponse("Please Register, User does not exist");
        } else {
            if (passwordEncoder.matches(newPassword, currentStudent.getPassword())) {
                return new ChangePasswordResponse("Old password cannot be the same as new password");
            } else {
                if (confirmPassword.equals(newPassword)) {
                    currentStudent.setPassword(passwordEncoder.encode(newPassword));
                    studentRepository.save(currentStudent);
                    return new ChangePasswordResponse("Password successfully changed");
                } else {
                    return new ChangePasswordResponse("Password must match");
                }
            }
        }
    }

    @Override
    public ResetPasswordResponse reset(ResetPasswordRequest resetPasswordRequest) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Student currentStudent = studentRepository.findByUserName(resetPasswordRequest.getUserName());
        String newPassword = resetPasswordRequest.getNewPassword();
        String confirmPassword = resetPasswordRequest.getConfirmPassword();

        if (currentStudent == null) {
            return new ResetPasswordResponse("Please Register, User does not exist");
        } else {
            if (passwordEncoder.matches(newPassword, currentStudent.getPassword())) {
                return new ResetPasswordResponse("Old password cannot be the same as new password");
            } else {
                if (confirmPassword.equals(newPassword)) {
                    currentStudent.setPassword(passwordEncoder.encode(newPassword));
                    studentRepository.save(currentStudent);
                    return new ResetPasswordResponse("Password successfully Reset");
                } else {
                    return new ResetPasswordResponse("Password must match");
                }
            }
        }
    }
}

//    @Override
//    public String GetStId(String userName) {
//            String Idstud = null;
//            Student stud = studentRepository.findByUserNameByRegNo(userName);
//            if(stud==null) {
//                logger.info("The student doesnt exist");
//            }
//            else{
//                Idstud  = stud.getRegNo();
//            }
//            return Idstud;
//        }



