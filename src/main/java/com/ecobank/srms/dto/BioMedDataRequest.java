package com.ecobank.srms.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
public class BioMedDataRequest {

    private Long studentId;

    @NotNull(message = "fName cannot be null")
    private String fName;

    @NotNull(message = "fName cannot be null")
    private String surName;

    private String midName;
    @NotNull(message = "JambNo cannot be null")
    @Size(min = 4,message = "jambNo must be more than 4")
    private String jambNo;

    @NotNull(message = "Date Of Birth cannot be null")
    @Pattern(regexp = "^\\d{4}/\\d{2}/\\d{2}$",message = "Date Format Not appropriate, must be YYYY/MM/DD")
    private String dateOfBirth;

    @NotNull(message = "age cannot be null")
    private String age;
    @NotNull(message = "sex cannot be null")
    private String sex;

    @NotNull(message = "Marital Status cannot be null")
    private String mStatus;

    @NotNull(message = "Department cannot be null")
    private String department;

    @NotNull(message = "faculty cannot be null")
    private String faculty;

    @NotNull(message = "address cannot be null")
    private String address;
    @Email
    private String email;


    @NotNull(message = "Phone Number cannot be null")
    @Pattern(regexp = "^\\+?\\d+$",message = "Number must be more than 1")
    @Size(min=10, max=16, message = "Number must be more than 10 and less than 16")
    private String phoneNo;

    @NotNull(message = "faculty cannot be null")
    private String nationality;

    @NotNull(message = "Religion cannot be null")
    private String religion;

    @NotNull(message = "State of Origin cannot be null")
    private String stOfOrg;

    @NotNull(message = "Local Government Area cannot be null")
    private String lga;

    @NotNull(message = "Parent Name cannot be null")
    private String parName;

    @NotNull(message = "Parent Occupation cannot be null")
    private String occName;

    @NotNull(message = "Parent Address cannot be null")
    private String parAdd;

    @Email
    private String parEmail;

    @Pattern(regexp = "^\\+?\\d+$",message = "Number must be more than 1")
    @Size(min=10, max=16)
    @NotNull(message = "Parent Phone Number cannot be null")
    private String parNO;

    @NotNull(message = "Picture cannot be null")
    private String picture;

   public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }



    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getMidName() {
        return midName;
    }

    public void setMidName(String midName) {
        this.midName = midName;
    }

    public String getJambNo() {
        return jambNo;
    }

    public void setJambNo(String jambNo) {
        this.jambNo = jambNo;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getmStatus() {
        return mStatus;
    }

    public void setmStatus(String mStatus) {
        this.mStatus = mStatus;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getStOfOrg() {
        return stOfOrg;
    }

    public void setStOfOrg(String stOfOrg) {
        this.stOfOrg = stOfOrg;
    }

    public String getLga() {
        return lga;
    }

    public void setLga(String lga) {
        this.lga = lga;
    }

    public String getParName() {
        return parName;
    }

    public void setParName(String parName) {
        this.parName = parName;
    }



    public String getOccName() {
        return occName;
    }

    public void setOccName(String occName) {
        this.occName = occName;
    }

    public String getParAdd() {
        return parAdd;
    }

    public void setParAdd(String parAdd) {
        this.parAdd = parAdd;
    }

    public String getParEmail() {
        return parEmail;
    }

    public void setParEmail(String parEmail) {
        this.parEmail = parEmail;
    }

    public String getParNO() {
        return parNO;
    }

    public void setParNO(String parNO) {
        this.parNO = parNO;
    }



}
