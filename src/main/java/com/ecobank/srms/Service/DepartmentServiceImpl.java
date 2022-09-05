package com.ecobank.srms.Service;

import com.ecobank.srms.model.Department;
import com.ecobank.srms.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service

public class DepartmentServiceImpl implements DepartmentService{

    @Autowired
    private DepartmentRepository departmentRepository;
    Logger logger = Logger.getLogger(DepartmentServiceImpl.class.getName());

    @Override
    public Long getDeptId(String dept_name) {
        Long dept_Id=null;
        Department dept= departmentRepository.findByDeptName(dept_name);
        if(dept==null) {
            logger.info("The department doesnt exist");
    }
        else{
           dept_Id= dept.getDept_id();
        }
        return dept_Id;
    }}
