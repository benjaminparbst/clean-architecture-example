package com.netcompany.guestlecture.softwaremaintenance.controller;

import com.netcompany.guestlecture.softwaremaintenance.domain.Vehicle;
import com.netcompany.guestlecture.softwaremaintenance.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {
    @Autowired
    private VehicleRepository vehicleRepository;

    @GetMapping("/{id}")
    public ResponseEntity<String> getVehicle(@PathVariable Long id) {
        Optional<Vehicle> vehicleOpt = vehicleRepository.findById(id);

        if (vehicleOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Vehicle not found with ID: " + id);
        }

        Vehicle vehicle = vehicleOpt.get();

        // Business logic mixed with data access
        if ("EXPIRED".equals(vehicle.getRegistrationStatus())) {
            return ResponseEntity.ok("Vehicle is not registered.");
        }

        return ResponseEntity.ok("Vehicle is registered.");
    }
}
