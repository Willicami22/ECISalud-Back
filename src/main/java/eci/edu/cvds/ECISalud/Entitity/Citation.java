package eci.edu.cvds.ECISalud.Entitity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "Citation")
public class Citation {
    @Id
    private String id;
    private LocalDate date;
    private String specialization;
    private String doctor;
    private String location;
    private String state;
    private String user;
    private String email;
    private String userId;

    public LocalDate getDate() {
        return date;
    }

    public String getDoctor() {
        return doctor;
    }

    public String getEmail() {
        return email;
    }

    public String getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    public String getSpecialization() {
        return specialization;
    }

    public String getState() {
        return state;
    }

    public String getUser() {
        return user;
    }

    public String getUserId() {
        return userId;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
