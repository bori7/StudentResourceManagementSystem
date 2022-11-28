package com.ecobank.srms.repository;

import com.ecobank.srms.SrmsApplication;
import com.ecobank.srms.model.Admin;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;



@RunWith(SpringRunner.class)
@SpringBootTest(classes = SrmsApplication.class)
@ComponentScan(basePackages = "com.ecobank.srms")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AdminRepositoryTest {

    @Autowired
    AdminRepository adminRepository;

    @Test
    void findById() {


    }

    @Test
    void findByUsername() {
        Date date = new Date();
        Admin admin = new Admin();
        admin.setUsername("TestAdminUser");
        admin.setPassword("TestAdminUserpassword");
        admin.setDate_Created(date);
        admin.setFName("TestFName");
        adminRepository.save(admin);
        Optional<Admin> admin_test = adminRepository.findByUsername("TestAdminUser");
        assertEquals(admin,admin_test.get());
    }
}