package com.ecobank.srms.repository;

import com.ecobank.srms.SrmsApplication;
import com.ecobank.srms.model.BioMedData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = SrmsApplication.class)
@ComponentScan(basePackages = "com.ecobank.srms")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BioMedDataRepositoryTest {

    @Autowired
    BioMedDataRepository bioMedDataRepository;

    @Test
    void findById() {

    }

    @Test
    void findBystudentId() {

    }

    @Test
    void findAllBystudentId() {

    }

    @Test
    void findByJambNo() {
        Optional<BioMedData> bioMedData = bioMedDataRepository.findByJambNo("20302030");
        assertEquals(true,bioMedData.isPresent());
    }

    @Test
    void updatePictureByJambNo() {
         bioMedDataRepository.updatePictureByJambNo("20302030","/Users/boma/Projects/srms/uploadsbiopic.png");
         String url = "/Users/boma/Projects/srms/uploadsbiopic.png";
         Optional<BioMedData> bioMedData = bioMedDataRepository.findByJambNo("20302030");
         String pic = bioMedData.get().getPicture();
         assertEquals(url,pic);

    }
}