package com.example.portfoliovaultv2.models;

import org.bson.Document;

public class Experience {
    private String startDate;
    private String endDate;
    private String company;
    private String role;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Experience(String startDate, String endDate, String company, String role) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.company = company;
        this.role = role;
    }

    public Document toDocument() {
        Document document = new Document();
        document.append("startDate", this.startDate);
        document.append("endDate", this.endDate);
        document.append("company", this.company);
        document.append("role", this.role);
        return document;
    }

    public static Experience documentToExperience(Document document){
        if(document == null){
            return null;
        }
        return new Experience(document.getString("startDate"),
                document.getString("endDate"),
                document.getString("company"),
                document.getString("role") );
    }
}