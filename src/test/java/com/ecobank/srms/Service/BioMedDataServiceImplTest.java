package com.ecobank.srms.Service;

import com.ecobank.srms.SrmsApplication;
import com.ecobank.srms.dto.*;
import com.ecobank.srms.model.BioMedData;
import com.ecobank.srms.repository.BioMedDataRepository;
import com.ecobank.srms.utils.ImageTrans;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SrmsApplication.class)
@ComponentScan(basePackages = "com.ecobank.srms")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BioMedDataServiceImplTest {

    @Autowired
    BioMedDataService bioMedDataService;

    @Autowired
    BioMedDataRepository bioMedDataRepository;

    @Test
    void save() throws IOException {
        InputStream input = new FileInputStream("src/main/resources/config.properties");
        Properties prop = new Properties();
        prop.load(input);
        String sValue = prop.getProperty("base64String");

        BioMedDataRequest bioMedDataRequest = new BioMedDataRequest();
        bioMedDataRequest.setAddress("JambTestAddress");
        bioMedDataRequest.setAge("20");
        bioMedDataRequest.setDateOfBirth("20-12-1998");
        bioMedDataRequest.setDepartment("Computer Science");
        bioMedDataRequest.setEmail("JambTestEmail@gmail.com");
        bioMedDataRequest.setFaculty("JambTestFaculty");
        bioMedDataRequest.setfName("JAMBFname");
        bioMedDataRequest.setJambNo("JambTest1001");
        bioMedDataRequest.setLga("JambTestLGA");
        bioMedDataRequest.setMidName("JambTestMidName");
        bioMedDataRequest.setmStatus("Married");
        bioMedDataRequest.setNationality("African");
        bioMedDataRequest.setOccName("JambOccName");
        bioMedDataRequest.setParAdd("JAmbTestParrAdd");
        bioMedDataRequest.setParEmail("JambTEstParrEmail@gmail.com");
        bioMedDataRequest.setParName("JambTestParName");
        bioMedDataRequest.setParNO("0902302920221");
        bioMedDataRequest.setPhoneNo("09092301923");
        bioMedDataRequest.setPicture((sValue));
        bioMedDataRequest.setReligion("Aethist");
        bioMedDataRequest.setSex("Male");
        bioMedDataRequest.setStOfOrg("JambTestOrigin");
        bioMedDataRequest.setSurName("JambTestSurname");

        BioMedDataResponse bioMedDataResponse_actual = bioMedDataService.save(bioMedDataRequest);

        BioMedDataResponse bioMedDataResponse_expected = BioMedDataResponse
                .builder()
                .message("Thank you")
                .build();

        Assert.assertEquals(bioMedDataResponse_expected,bioMedDataResponse_actual);
    }

    @Test
    void update() throws IOException {
        InputStream input = new FileInputStream("src/main/resources/config.properties");
        Properties prop = new Properties();
        prop.load(input);
        String sValue = prop.getProperty("base64String");
        BioMedDataRequest bioMedDataRequest = new BioMedDataRequest();
        bioMedDataRequest.setAddress("JambTestAddress");
        bioMedDataRequest.setAge("20");
        bioMedDataRequest.setDateOfBirth("20-12-1998");
        bioMedDataRequest.setDepartment("Computer Science");
        bioMedDataRequest.setEmail("JambTestEmail@gmail.com");
        bioMedDataRequest.setFaculty("JambTestFaculty");
        bioMedDataRequest.setfName("JAMBFnameUpdated");
        bioMedDataRequest.setJambNo("JambTest1001");
        bioMedDataRequest.setLga("JambTestLGA");
        bioMedDataRequest.setMidName("JambTestMidName");
        bioMedDataRequest.setmStatus("Married");
        bioMedDataRequest.setNationality("African");
        bioMedDataRequest.setOccName("JambOccName");
        bioMedDataRequest.setParAdd("JAmbTestParrAdd");
        bioMedDataRequest.setParEmail("JambTEstParrEmail@gmail.com");
        bioMedDataRequest.setParName("JambTestParName");
        bioMedDataRequest.setParNO("0902302920221");
        bioMedDataRequest.setPhoneNo("09092301923");
        bioMedDataRequest.setReligion("Christian");
        bioMedDataRequest.setSex("Male");
        bioMedDataRequest.setStOfOrg("JambTestOrigin");
        bioMedDataRequest.setSurName("JambTestSurname");
        bioMedDataRequest.setPicture((sValue));

        BioMedDataResponse bioMedDataResponse_actual = bioMedDataService.update(bioMedDataRequest);

        BioMedDataResponse bioMedDataResponse_expected = BioMedDataResponse
                .builder()
                .message("Form Updated")
                .build();

        Assert.assertEquals(bioMedDataResponse_expected,bioMedDataResponse_actual);

    }

    @Test
    void display() throws Exception {
        BioMedDataRequest bioMedDataRequest = new BioMedDataRequest();
        bioMedDataRequest.setJambNo("JambTest1001");

        BioMedData biodata = bioMedDataRepository.findByJambNo(bioMedDataRequest.getJambNo()).orElse(null);



        ProfileResponse profileResponse_actual = bioMedDataService.display(bioMedDataRequest);

        System.out.println("Profile response Actual " + profileResponse_actual);


        ProfileResponse profileResponse_expected = ProfileResponse
                .builder()
                .picture(biodata.getPicture())
                .age(biodata.getAge())
                .department(biodata.getDepartment())
                .sex(biodata.getSex())
                .faculty(biodata.getFaculty())
                .stOfOrg(biodata.getStOfOrg())
                .dateOfBirth(biodata.getDateOfBirth())
                .surName(biodata.getSurName())
                .fName(biodata.getFName())
                .lga(biodata.getLga())
                .midName(biodata.getMidName())
                .message("Profile retrived")
                .surName(biodata.getSurName())
                .build();

        System.out.println("Profile response Expected " + profileResponse_expected);
        Assert.assertEquals(profileResponse_actual,profileResponse_expected);
    }

    @Test
    void storeImage() throws IOException {
        InputStream input = new FileInputStream("src/main/resources/config.properties");
        Properties prop = new Properties();
        prop.load(input);
        String sValue = prop.getProperty("base64String");
        MultipartFile image = ImageTrans.base64ToMultipart(sValue);
        File file = bioMedDataService.storeImage(image,"biopic" );
        System.out.println("Hello hello" + file);
        Assert.assertEquals(file.getName(),"biopic.jpg");
    }

    @Test
    void compressJpgImage() throws IOException {
        File myObj = new File("uploads/biopic.png");
       File return_file = bioMedDataService.CompressJpgImage(myObj);
       Assert.assertNotNull(return_file);

    }

    @Test
    void uploadUri() throws IOException {
        InputStream input = new FileInputStream("src/main/resources/config.properties");
        Properties prop = new Properties();
        prop.load(input);
        String sValue = prop.getProperty("base64String");

        String Url = bioMedDataService.uploadUri(sValue);

        Assert.assertNotNull(Url);
    }

    @Test
    void upload() throws IOException {
        InputStream input = new FileInputStream("src/main/resources/config.properties");
        Properties prop = new Properties();
        prop.load(input);
        String sValue = prop.getProperty("base64String");
        UploadPictureRequest uploadPictureRequest = new UploadPictureRequest();
        uploadPictureRequest.setJambNo("JambTest1001");
        uploadPictureRequest.setPicture(sValue);
        BioMedData biodata = bioMedDataRepository.findByJambNo(uploadPictureRequest.getJambNo()).orElse(null);

        BioMedDataResponse bioMedDataResponse_actual = bioMedDataService.upload(uploadPictureRequest);
        BioMedDataResponse bioMedDataResponse_expected = BioMedDataResponse
                .builder()
                .url(biodata.getPicture())
                .jambNo(biodata.getJambNo())
                .build();

        Assert.assertEquals(bioMedDataResponse_actual,bioMedDataResponse_expected);

    }

    @Test
    void getPic() {
    String Url = bioMedDataService.getPic("JambTest1001");
        Assert.assertNotNull(Url);
    }

    @Test
    void displayPic() throws IOException {
        DisplayPictureRequest displayPictureRequest = new DisplayPictureRequest();
        displayPictureRequest.setJambNo("JambTest1001");
        BioMedData biodata = bioMedDataRepository.findByJambNo(displayPictureRequest.getJambNo()).orElse(null);

        DisplayPictureResponse displayPictureResponse_actual = bioMedDataService.displayPic(displayPictureRequest);

        DisplayPictureResponse displayPictureResponse_expected = DisplayPictureResponse.
                builder()
                .code("00")
                .message("Picture Displayed")
                .picUrl(biodata.getPicture())
                .build();

        Assert.assertEquals(displayPictureResponse_actual,displayPictureResponse_expected);
    }
}