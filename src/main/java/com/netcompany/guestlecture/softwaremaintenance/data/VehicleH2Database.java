package com.netcompany.guestlecture.softwaremaintenance.data;

import com.netcompany.guestlecture.softwaremaintenance.data.jpa.Vehicle;
import com.netcompany.guestlecture.softwaremaintenance.domain.VehicleEntity;
import com.netcompany.guestlecture.softwaremaintenance.data.repository.VehicleRepository;
import com.netcompany.guestlecture.softwaremaintenance.usecase.interfaces.VehicleDataSource;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class VehicleH2Database implements VehicleDataSource {
    private final VehicleRepository vehicleRepository;

    public VehicleH2Database(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public Optional<VehicleEntity> getVehicleEntity(Long vehicleId) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(vehicleId);

        return vehicle.map(value -> new VehicleEntity(
                value.getId(),
                value.getRegistrationStatus()
        ));

    }
}
