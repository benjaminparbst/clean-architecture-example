package com.netcompany.guestlecture.softwaremaintenance.usecase.interfaces;

import com.netcompany.guestlecture.softwaremaintenance.domain.VehicleEntity;

import java.util.Optional;


public interface VehicleDataSource {
    Optional<VehicleEntity> getVehicleEntity(Long id);
}
