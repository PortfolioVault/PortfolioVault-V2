package com.example.portfoliovaultv2.models;

public class Education {
    private String diplomat;
    private String university;
    private String yearOfObtention;
    private String idUser;

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
        this.idUser = idUser;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }
}
