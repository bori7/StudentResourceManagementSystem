package com.ecobank.srms.Service;

import com.ecobank.srms.dto.*;

import java.io.IOException;

public interface StudentService {

    StudentResponse Register(StudentRequest studentRequest) throws IOException;

    StudentResponse Login(LoginRequest loginRequest) throws IOException;

    ChangePasswordResponse updateCurrentPassword(ChangePasswordRequest changePasswordRequest);

    ResetPasswordResponse reset(ResetPasswordRequest resetPasswordRequest);

    //String GetStId (String userName);
}
