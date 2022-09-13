package com.ecobank.srms.repository;
import com.ecobank.srms.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
//@Qualifier("Student")
public interface StudentRepository extends JpaRepository<Student,Long> {
    Optional<Student> findPersonByJambNo(String jambNo);

    Student findByJambNo(String JambNo);

    //Student findByUserNameByRegNo(String userName);

}
