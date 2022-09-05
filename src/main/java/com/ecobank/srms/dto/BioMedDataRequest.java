package com.ecobank.srms.dto;

import com.ecobank.srms.model.Student;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BioMedDataRequest {

    private Long studentId;
    private String fName;
    private String surName;
    private String midName;
    private String jambNo;
    private String dateOfBirth;
    private String age;
    private String sex;
    private String mStatus;
    private String department;
    private String faculty;
    private String address;
    private String email;
    private String phoneNo;
    private String nationality;
    private String religion;
    private String stOfOrg;
    private String lga;
    private String parName;
    private String occName;
    private String parAdd;
    private String parEmail;
    private String parNO;

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
