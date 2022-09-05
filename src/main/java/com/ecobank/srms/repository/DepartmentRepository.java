package com.ecobank.srms.repository;

import com.ecobank.srms.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long> {
    Department findByDeptName(String DeptName);
}
