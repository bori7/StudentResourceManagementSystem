package com.ecobank.srms.repository;
import com.ecobank.srms.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
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



}
