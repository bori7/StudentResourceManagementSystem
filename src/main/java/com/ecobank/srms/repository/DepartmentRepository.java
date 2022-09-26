package com.ecobank.srms.repository;

import com.ecobank.srms.model.CourseManage;
import com.ecobank.srms.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long> {
    Department findByDeptName(String DeptName);

    List<Department> findAll();
}
