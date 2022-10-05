package com.ecobank.srms.Service;

import com.ecobank.srms.dto.AdminCountDeptDisplayResponse;
import com.ecobank.srms.dto.AdminCountStudDeptRequest;
import com.ecobank.srms.dto.AdminCountStudDisplayResponse;
import com.ecobank.srms.model.Courses;
import com.ecobank.srms.model.Department;
import com.ecobank.srms.model.Student;
import com.ecobank.srms.model.ViewCourse;
import com.ecobank.srms.repository.DepartmentRepository;
import com.ecobank.srms.repository.StudentRepository;
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

    @Autowired
    StudentRepository studentRepository;

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

    @Override
    public AdminCountDeptDisplayResponse countDept() {
        Long count = departmentRepository.count();
        return AdminCountDeptDisplayResponse.builder()
                .message("Successful")
                .response("These are the departments that exist")
                .count(count)
                .build();
    }

    @Override
    public AdminCountStudDisplayResponse displaycountStudDept(AdminCountStudDeptRequest adminCountStudDeptRequest) {
        Department department = departmentRepository.findByDeptName(adminCountStudDeptRequest.getDeptName());
        List<Student> student = studentRepository.findByDepartment(adminCountStudDeptRequest.getDeptName());

        if (department == null) {
            return AdminCountStudDisplayResponse.builder().message("There is no department with that name").build();
        }


        List<Object> studView = new ArrayList<>();

        if (student.isEmpty()) {
            return AdminCountStudDisplayResponse.builder().message("There are no students").build();
        } else {
            for (int i = 0; i < student.size(); i++) {
                studView.add(student.get(i));
            }
            return AdminCountStudDisplayResponse.builder()
                    .response("These are the number of students in the department")
                    .count((long) studView.size())
                    .message("Successful")
                    .build();
        }
    }
}
