package com.ecobank.srms.Service;

import com.ecobank.srms.model.Courses;
import com.ecobank.srms.model.Department;
import com.ecobank.srms.model.ViewCourse;
import com.ecobank.srms.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
    }

    @Override
    public  Object displayDept() {
        List<Department> dept = departmentRepository.findAll();
         List<Object> deptView = new ArrayList<>();
        if (dept==null){
            return "There are no Departments";
        }
        else{
            for (int i = 0; i < dept.size(); i++){
                deptView.add(dept.get(i));
            }
            return deptView;
        }
    }

}
