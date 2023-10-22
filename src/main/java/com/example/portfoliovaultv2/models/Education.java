package com.example.portfoliovaultv2.models;

import org.bson.Document;

public class Education {
    private String diplomat;
    private String university;
    private String yearOfObtention;
    private String emailUser;

    public static Education documentToEducation(Document document){
        if(document == null){
            return null;
        }
        Education education = new Education(document.getString("university"),document.getString("diplomat"), document.getString("yearOfObtention"), document.getString("emailUser"));
//        education.university =  document.getString("university");
//        education.diplomat = document.getString("diplomat");
//        education.yearOfObtention = document.getString("yearOfObtention");
//        education.emailUser =  document.getString("emailUser");
//

        return education;
//
//        this.diplomat = userSession.getEmail();
//        this.emailUser = education.getEmailUser() != null ? education.getEmailUser() : "";
//        this.yearOfObtention = education.getYearOfObtention() != null ?  education.getYearOfObtention() : "";
//        this.university = education.getUniversity() != null ? education.getUniversity() : "";
//
    }

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
}
