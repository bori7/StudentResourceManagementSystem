package com.ecobank.srms.Service;

import com.ecobank.srms.dto.*;
import com.ecobank.srms.utils.Token;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public interface StudentService {

    StudentResponse Register(StudentRequest studentRequest) throws IOException;

    StudentResponse Login(LoginRequest loginRequest) throws IOException;

    ChangePasswordResponse updateCurrentPassword(ChangePasswordRequest changePasswordRequest);

    Token extractToken(HttpServletRequest httpServletRequest);

    ResetPasswordResponse reset(ResetPasswordRequest resetPasswordRequest);

    Object displayStud();

   AdminCountStudDisplayResponse countStud() throws IOException;

    List<Object> displayCountStudbyDept();


    AdminFindStudentResponse showCountLevelByDepartment(AdminFindStudentRequest adminFindStudentRequest);

    AdminFindStudentResponse showCountLevelByDepartment(String deptName);

    AdminStudentGeneralResponse ShowCountOldStudents();

    AdminStudentGeneralResponse ShowCountNewStudents();
}
