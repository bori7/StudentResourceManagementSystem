package com.ecobank.srms.repository;

import com.ecobank.srms.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface TeacherRepository extends JpaRepository <Teacher,Long> {
    Optional<Teacher> findByUsername(String username);

    Optional<Teacher> findByEmail(String email);

}
