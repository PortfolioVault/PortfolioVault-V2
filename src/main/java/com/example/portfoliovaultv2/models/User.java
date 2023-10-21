package com.example.portfoliovaultv2.models;

import org.bson.Document;

import java.util.LinkedList;

public class User {
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String professionalTitle;
    private String phoneNumber;
    private String address;
    private String age;
    private LinkedList<Education> educations;

    public String getAge() {
        return age;
    }


    public String getFirstName() {
        return firstname;
    }


    public String getLastName() {
        return lastname;
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

    public String getProfessionalTitle() {
        return professionalTitle;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public LinkedList<Education> getEducations() {
        return educations;
    }

    public User(String firstname, String lastname, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstname + '\'' +
                ", lastName='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public static User documentToUser(Document document){
        if(document == null){
            return null;
        }
        User user = new User(document.getString("firstname"),document.getString("lastname"),document.getString("email") );
        user.professionalTitle = document.getString("professionalTitle");
        user.phoneNumber = document.getString("phoneNumber");
        user.address = document.getString("address");
        user.age = document.getString("age");
        user.password = document.getString("password");
        return user;
    }
}
