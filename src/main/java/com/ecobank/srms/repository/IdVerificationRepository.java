package com.ecobank.srms.repository;

import com.ecobank.srms.model.IdVerification;
import com.ecobank.srms.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IdVerificationRepository  extends JpaRepository<IdVerification,Long> {
    Optional<IdVerification> findByuserId(String userId);
}
