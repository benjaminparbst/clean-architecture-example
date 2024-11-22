package com.netcompany.guestlecture.softwaremaintenance.data.jpa;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Vehicle {
    @Id
    private Long id;

    private String registrationStatus;

    public String getRegistrationStatus() {
        return registrationStatus;
    }

    public void setRegistrationStatus(String registrationStatus) {
        this.registrationStatus = registrationStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
