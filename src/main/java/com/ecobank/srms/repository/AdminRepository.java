package com.ecobank.srms.repository;

import com.ecobank.srms.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository <Admin,Long>{
    @Override
    Optional<Admin> findById(Long aLong);

    Optional<Admin> findByUsername(String username);

    //Optional<Admin> findByEmail(String email);
}
