package com.ecobank.srms.repository;
import com.ecobank.srms.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
//@Qualifier("Student")
public interface StudentRepository extends JpaRepository<Student,Long> {
    Optional<Student> findPersonByRegNo(String RegNo);

    Student findByUserName(String userName);

    //Student findByUserNameByRegNo(String userName);

}
