package com.example.portfoliovaultv2.models;

import java.util.LinkedList;

public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String professionalTitle;
    private String phoneNumber;
    private String address;
    private String age;
    private LinkedList<Experience> experiences;
    private LinkedList<Education> educations;

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfessionalTitle() {
        return professionalTitle;
    }

    public void setProfessionalTitle(String professionalTitle) {
        this.professionalTitle = professionalTitle;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LinkedList<Experience> getExperiences() {
        return experiences;
    }

    public void setExperiences(LinkedList<Experience> experiences) {
        this.experiences = experiences;
    }

    public LinkedList<Education> getEducations() {
        return educations;
    }

    public void setEducations(LinkedList<Education> educations) {
        this.educations = educations;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
