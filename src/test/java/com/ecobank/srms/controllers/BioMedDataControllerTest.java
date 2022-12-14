package com.ecobank.srms.controllers;

import com.ecobank.srms.Service.CourseManageService;
import com.ecobank.srms.dto.*;
import com.ecobank.srms.utils.Token;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.HttpServletRequest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BioMedDataControllerTest {
    private String globaltoken ="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJxZGZzeXJ0aXlqdHlmZHJydHlmaHI1dWk3eXRqaCIsImlzcyI6IkFueXRoaW5nIiwiZXhwIjoxNjY5Mjg0NDg5fQ.b_2lrE9GnREFgQX-JcgxM9N0oIKArfpIghYwODitnI0Y53w_Mq3en5JpF_0TRyM4-v47XSRMA5yakdOxljBx8Q";


    @Autowired
    HttpServletRequest httpServletRequest;

    @Autowired
    CourseManageService courseManageService;

    @LocalServerPort
    private int port;

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    Token token;
    String accessToken;

    @Test
    void save() throws IOException {
        InputStream input = new FileInputStream("src/main/resources/config.properties");
            Properties prop = new Properties();
            prop.load(input);
            String sValue = prop.getProperty("base64String");

        headers.setBearerAuth(globaltoken);
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

        String actual = "{\"message\":\"Thank you\"}";

        HttpEntity<StudentRequest> entity = new HttpEntity(bioMedDataRequest, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/v1/student/save_biodata"), HttpMethod.POST, entity ,String.class);

        Assert.assertEquals(response.getBody().toString().trim(),actual.trim());
        Assert.assertEquals(response.getStatusCodeValue(),201);
    }


    @Test
    void update() throws IOException {
        headers.setBearerAuth(globaltoken);
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
        bioMedDataRequest.setReligion("Christian");
        bioMedDataRequest.setSex("Male");
        bioMedDataRequest.setStOfOrg("JambTestOrigin");
        bioMedDataRequest.setSurName("JambTestSurname");
        bioMedDataRequest.setPicture((sValue));

        String actual = "{\"message\":\"Form Updated\"}";


        HttpEntity<StudentRequest> entity = new HttpEntity(bioMedDataRequest, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/v1/student/update_biodata"), HttpMethod.PUT, entity ,String.class);

        Assert.assertEquals(response.getBody().toString().trim(),actual.trim());

    }

    @Test
    void displayProfile() {
        headers.setBearerAuth(globaltoken);
        ProfileResponse profileResponse = new ProfileResponse();
        profileResponse.setJambNo("JambTest1001");



        String actual = "{\"surName\":\"JambTestSurname\",\"sex\":\"Male\",\"department\":\"Computer Science\",\"faculty\":\"JambTestFaculty\",\"dateOfBirth\":\"20-12-1998\",\"picture\":\"http://res.cloudinary.com/bomacloudsdatabase/image/upload/v1669281014/o83kkeq3fcxeziryocwb.png\",\"midName\":\"JambTestMidName\",\"age\":\"20\",\"lga\":\"JambTestLGA\",\"stOfOrg\":\"JambTestOrigin\",\"message\":\"Profile retrived\",\"fname\":\"JAMBFname\"}";

        HttpEntity<StudentRequest> entity = new HttpEntity(profileResponse, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/v1/student/profile"), HttpMethod.POST, entity ,String.class);

        Assert.assertEquals(response.getBody().toString().trim(),actual.trim());

    }

    @Test
    void upload() throws IOException {
        headers.setBearerAuth(globaltoken);
        InputStream input = new FileInputStream("src/main/resources/config.properties");
        Properties prop = new Properties();
        prop.load(input);
        String sValue = prop.getProperty("base64String");
        UploadPictureRequest uploadPictureRequest = new UploadPictureRequest();
        uploadPictureRequest.setJambNo("JambTest1001");
        uploadPictureRequest.setPicture(sValue);
        String actual = "{\"message\":\"Picture Uploaded\",\"url\":\"http://res.cloudinary.com/bomacloudsdatabase/image/upload/v1669282434/c2yuqzyk7djbxvrss7tg.png\",\"jambNo\":\"JambTest1001\"}";


        HttpEntity<StudentRequest> entity = new HttpEntity(uploadPictureRequest, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/v1/student/upload_biodata_picture"), HttpMethod.POST, entity ,String.class);

        Assert.assertEquals(response.getBody().trim(),actual.trim());

    }

    @Test
    void displayPic() {
        headers.setBearerAuth(globaltoken);
        DisplayPictureRequest displayPictureRequest = new DisplayPictureRequest();
        displayPictureRequest.setJambNo("JambTest1001");

        HttpEntity<StudentRequest> entity = new HttpEntity(displayPictureRequest, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/v1/student/display_biodata_picture"), HttpMethod.POST, entity ,String.class);

        String actual = "{\"message\":\"Picture Uploaded\",\"url\":\"http://res.cloudinary.com/bomacloudsdatabase/image/upload/v1669282434/c2yuqzyk7djbxvrss7tg.png\"}";



        Assert.assertEquals(response.getBody().toString().trim(),actual.trim());


    }
}