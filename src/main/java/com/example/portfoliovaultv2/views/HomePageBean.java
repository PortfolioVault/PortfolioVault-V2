package com.example.portfoliovaultv2.views;



import com.example.portfoliovaultv2.models.User;
import com.example.portfoliovaultv2.services.UserServiceEJB;
import com.example.portfoliovaultv2.session.UserSession;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.logging.Logger;

@Named
@ViewScoped
public class HomePageBean implements Serializable {
    @Inject
    private UserServiceEJB userServiceEJB;
    @Inject
    private UserSession userSession;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;
    private String age;
    private String professionalTitle;
    Logger logger = Logger.getLogger(HomePageBean.class.getName());


    @PostConstruct
    public void init() {
        // Initialize properties using values from UserSessionBean
        User user = userServiceEJB.findUserByEmail(userSession.getEmail());
        logger.info(user.toString());
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = userSession.getEmail();
        this.address = user.getAddress() != null ? user.getAddress() : "";
        this.age = user.getAge() != null ? user.getAge() : "";
        this.professionalTitle = user.getProfessionalTitle() != null ? user.getProfessionalTitle() : "";
        this.phoneNumber = user.getPhoneNumber() != null ? user.getPhoneNumber() : "";
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getProfessionalTitle() {
        return professionalTitle;
    }

    public void setProfessionalTitle(String professionalTitle) {
        this.professionalTitle = professionalTitle;
    }

    public void savePersonalInfos(){

    }
}
