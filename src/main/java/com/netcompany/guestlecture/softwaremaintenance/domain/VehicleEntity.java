package com.netcompany.guestlecture.softwaremaintenance.domain;

public class VehicleEntity {
    private Long id;
    private String status;

    public VehicleEntity(Long id, String status) {
        this.id = id;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }
}
