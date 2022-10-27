package com.ecobank.srms.repository;
import com.ecobank.srms.model.Department;
import com.ecobank.srms.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
//@Qualifier("Student")
public interface StudentRepository extends JpaRepository<Student,Long> {
    Optional<Student> findPersonByJambNo(String jambNo);
    Optional<Student> findPersonByEmail(String email);


    Student findByJambNo(String JambNo);

    List<Student> findByLevel(String level);

    List<Student> findAll();

    List<Student> findByDepartment(String deptName);

    long count();


    @Query(value = "select dept, count(*) from student GROUP BY dept ", nativeQuery = true)
    List<Object> findByDepartmentAndStudent();
    @Query(value = "select level, count(*) from student  WHERE dept = :deptName GROUP BY level", nativeQuery = true)
    List<Object> findLevelByDepartmentAndStudent(String deptName);

}
