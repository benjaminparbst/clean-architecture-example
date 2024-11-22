package com.netcompany.guestlecture.softwaremaintenance.controller;

import com.netcompany.guestlecture.softwaremaintenance.domain.VehicleEntity;
import com.netcompany.guestlecture.softwaremaintenance.usecase.GetVehicleUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {
    private final GetVehicleUseCase getVehicleUseCase;

    public VehicleController(GetVehicleUseCase getVehicleUseCase) {
        this.getVehicleUseCase = getVehicleUseCase;
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getVehicleStatus(@PathVariable Long id) {
        Optional<VehicleEntity> vehicle = getVehicleUseCase.process(id);

        if (vehicle.isEmpty()) {
            return ResponseEntity.badRequest().body("Vehicle not found with ID: " + id);
        }

        // Business logic mixed with data access
        if ("EXPIRED".equals(vehicle.get().getStatus())) {
            return ResponseEntity.ok("Vehicle is not registered.");
        }

        return ResponseEntity.ok("Vehicle is registered.");
    }
}
