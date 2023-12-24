package com.example.portfoliovaultv2.models;

import org.bson.Document;

public class Education {
    private String id;
    private String diplomat;
    private String university;
    private String yearOfObtention;
    private String emailUser;



    public String getDiplomat() {
        return diplomat;
    }

    public void setDiplomat(String diplomat) {
        this.diplomat = diplomat;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getYearOfObtention() {
        return yearOfObtention;
    }

    public void setYearOfObtention(String yearOfObtention) {
        this.yearOfObtention = yearOfObtention;
    }

    public Education(String diplomat, String university, String yearOfObtention, String idUser) {
        this.diplomat = diplomat;
        this.university = university;
        this.yearOfObtention = yearOfObtention;
        this.emailUser = idUser;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String idUser) {
        this.emailUser = idUser;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}