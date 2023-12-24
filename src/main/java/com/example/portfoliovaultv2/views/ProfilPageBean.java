
package com.example.portfoliovaultv2.views;

import com.example.portfoliovaultv2.activeMQ.SimpleQueue;
import com.example.portfoliovaultv2.models.Education;
import com.example.portfoliovaultv2.models.Experience;
import com.example.portfoliovaultv2.models.User;
import com.example.portfoliovaultv2.services.EducationService;
import com.example.portfoliovaultv2.services.ExperienceServiceEJB;
import com.example.portfoliovaultv2.services.UserServiceEJB;
import com.example.portfoliovaultv2.session.UserSession;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.File;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Named
@ViewScoped
public class ProfilPageBean implements Serializable {
    @Inject
    private UserServiceEJB userServiceEJB;
    @Inject
    private ExperienceServiceEJB experienceServiceEJB;
    @Inject
    private EducationService educationService;
    @Inject
    private UserSession userSession;


    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;
    private String age;
    private String professionalTitle;
    private LinkedList<Experience> experiences = new LinkedList<>();
    private LinkedList<Education> educations = new LinkedList<>();
    private static final String QUEUE_NAME = "pendingPostulat";


    public void send() {
        try {
            // Chemin du fichier PDF dans votre projet
            String filePath = "D:\\S5\\benHaddy\\fatiPortFV2\\PortfolioVault-V2\\src\\main\\webapp\\public\\DeployingMERNapplication.pdf";
System.out.println("firstName"+ firstName);
            // Lire le fichier en tant qu'octets
            byte[] fileBytes = Files.readAllBytes(new File(filePath).toPath());
            firstName="soumia";
            lastName="ouafi";
            // Convertir le nom et le prénom en octets
            byte[] nameBytes = (firstName + "<|>" + lastName).getBytes(StandardCharsets.UTF_8);

            // Créer un tableau combiné
            byte[] combinedBytes = new byte[nameBytes.length + fileBytes.length];
            System.arraycopy(nameBytes, 0, combinedBytes, 0, nameBytes.length);
            System.arraycopy(fileBytes, 0, combinedBytes, nameBytes.length, fileBytes.length);

            // Envoyer le contenu combiné en tant que tableau d'octets
            SimpleQueue queue = new SimpleQueue(QUEUE_NAME);
            System.out.println("le message aa envoyer  est**** " + Arrays.toString(combinedBytes));

            queue.send(combinedBytes);
            queue.close();
        } catch (Exception e) {
            e.printStackTrace(); // Gérer les erreurs correctement dans un environnement de production
        }
    }

    public void getUser() {
        // Initialize properties using values from UserSessionBean
        User user = userServiceEJB.findUserByEmail(userSession.getEmail());
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = userSession.getEmail();
        this.address = user.getAddress() != null ? user.getAddress() : "";
        this.age = user.getAge() != null ? user.getAge() : "";
        this.professionalTitle = user.getProfessionalTitle() != null ? user.getProfessionalTitle() : "";
        this.phoneNumber = user.getPhoneNumber() != null ? user.getPhoneNumber() : "";
//        this.experiences = experienceServiceEJB.getExperiences(userSession.getEmail());
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

    public List<Experience> getExperiences() {
        return experienceServiceEJB.getExperiences(userSession.getEmail());
    }

    public void setExperiences(LinkedList<Experience> experiences) {
        this.experiences = experiences;
    }

    public List<Education> getEducations() {
        return educationService.getAllEducations(this.email);
    }

    public void setEducations(LinkedList<Education> educations) {
        this.educations = educations;
    }
}


