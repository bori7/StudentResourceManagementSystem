package com.ecobank.srms.Service;

import com.ecobank.srms.dto.*;
import com.ecobank.srms.exceptions.GenericException;
import com.ecobank.srms.model.Teacher;
import com.ecobank.srms.repository.TeacherRepository;
import com.ecobank.srms.utils.ResponseCodes;
import com.ecobank.srms.utils.Token;
import net.bytebuddy.asm.Advice;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    HttpServletRequest httpServletRequest;
    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    StudentServiceImpl studentService;

    @Override
    public TeacherRegisterResponse register(TeacherRegisterRequest teacherRegisterRequest) {
        boolean isPresent = teacherRepository.findByUsername(teacherRegisterRequest.getUsername()).isPresent();
        boolean isPresentEmail = teacherRepository.findByEmail(teacherRegisterRequest.getEmail()).isPresent();

        if ((isPresent)) {
            throw new GenericException(ResponseCodes.ALREADY_EXIST, "The Username : " + teacherRegisterRequest.getUsername() + " Already Exists", HttpStatus.BAD_REQUEST);

//            return TeacherRegisterResponse.builder()
//                    .message("The Username : " + teacherRegisterRequest.getUsername() + " Already Exists")
//                    .build();
        }

        if ((isPresentEmail)) {
            throw new GenericException(ResponseCodes.ALREADY_EXIST, "The Email " + teacherRegisterRequest.getEmail() + " Already Exists", HttpStatus.BAD_REQUEST);


//            return TeacherRegisterResponse.builder()
//
//                    .message("The Email " + teacherRegisterRequest.getEmail() + " Already Exists")
//                    .build();
        } else {
            String password = teacherRegisterRequest.getPassword();
            String confirmPassword = teacherRegisterRequest.getConfirmPassword();
            if (!(password.equals(confirmPassword))) {
                throw new GenericException(ResponseCodes.INVALID_CREDENTIAL, "Password must match confirm Password", HttpStatus.UNAUTHORIZED);


            } else {
                Teacher teacher = new Teacher();
                ModelMapper modelMapper = new ModelMapper();
                BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
                final String encodedPassword = bcryptPasswordEncoder.encode(teacherRegisterRequest.getPassword());
                teacherRegisterRequest.setPassword(encodedPassword);
                Date date = new Date();
                teacherRegisterRequest.setDate_Created(date);
                modelMapper.map(teacherRegisterRequest, teacher);
                teacherRepository.save(teacher);
            }


        }


        return TeacherRegisterResponse.builder().message("Teacher has registered")
                .username(teacherRegisterRequest.getUsername())
                .email(teacherRegisterRequest.getEmail())
                .build();
    }

    @Override
    public TeacherLoginResponse login(TeacherLoginRequest teacherLoginRequest) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        boolean ispresent = teacherRepository.findByUsername(teacherLoginRequest.getUsername()).isPresent();
        Teacher teacher = teacherRepository.findByUsername(teacherLoginRequest.getUsername()).get();

        if (!(ispresent)) {
            throw new GenericException(ResponseCodes.NOT_FOUND, "Please Register / User does not exist", HttpStatus.NOT_FOUND);

           // TeacherLoginResponse.builder().message("Please Register / User does not exist").build();
        } else {
            if (!passwordEncoder.matches(teacherLoginRequest.getPassword(), teacher.getPassword())) {

                throw new GenericException(ResponseCodes.INVALID_CREDENTIAL, "Incorrect Password", HttpStatus.UNAUTHORIZED);
                //return TeacherLoginResponse.builder().message("Incorrect Password").build();
            }

        }
        Token token = new Token();
        token = studentService.extractToken(httpServletRequest);

        return TeacherLoginResponse.builder()
                .message("Login Successful")
                .Response("00")
                .token(token.getAccessToken())
                .matric(teacher.getId_no())
                .username(teacher.getUsername())
                .build();
    }
}
