package com.netcompany.guestlecture.softwaremaintenance.usecase;

import com.netcompany.guestlecture.softwaremaintenance.domain.VehicleEntity;
import com.netcompany.guestlecture.softwaremaintenance.usecase.interfaces.VehicleDataSource;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class GetVehicleUseCase implements UseCase<Long, VehicleEntity> {
    private final VehicleDataSource vehicleDataSource;

    public GetVehicleUseCase(VehicleDataSource vehicleDataSource) {
        this.vehicleDataSource = vehicleDataSource;
    }

    @Override
    public Optional<VehicleEntity> process(Long id) {
        return vehicleDataSource.getVehicleEntity(id);
    }
}
