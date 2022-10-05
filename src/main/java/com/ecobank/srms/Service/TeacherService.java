package com.ecobank.srms.Service;

import com.ecobank.srms.dto.TeacherLoginRequest;
import com.ecobank.srms.dto.TeacherLoginResponse;
import com.ecobank.srms.dto.TeacherRegisterRequest;
import com.ecobank.srms.dto.TeacherRegisterResponse;

public interface TeacherService {
    TeacherRegisterResponse register(TeacherRegisterRequest teacherRegisterRequest);

    TeacherLoginResponse login(TeacherLoginRequest teacherLoginRequest);
}
