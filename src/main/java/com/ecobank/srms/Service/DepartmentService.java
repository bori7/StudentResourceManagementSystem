package com.ecobank.srms.Service;

import com.ecobank.srms.model.Department;

import java.util.List;

public interface DepartmentService {

    Long getDeptId(String dept_name);


    Object displayDept();
}
