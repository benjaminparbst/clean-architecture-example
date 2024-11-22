package com.netcompany.guestlecture.softwaremaintenance.usecase;

import com.netcompany.guestlecture.softwaremaintenance.domain.VehicleEntity;
import com.netcompany.guestlecture.softwaremaintenance.usecase.interfaces.VehicleDataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
class GetVehicleUseCaseTest {
    @Mock
    VehicleDataSource vehicleDataSource;
    @InjectMocks
    private GetVehicleUseCase getVehicleUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        Mockito.doReturn(Optional.of(new VehicleEntity(1L, "VALID"))).when(vehicleDataSource).getVehicleEntity(1L);
        Mockito.doReturn(Optional.of(new VehicleEntity(2L, "EXPIRED"))).when(vehicleDataSource).getVehicleEntity(2L);
        Mockito.doReturn(Optional.empty()).when(vehicleDataSource).getVehicleEntity(3L);
    }

    @Test
    void givenValidId_whenGetVehicleUseCaseProcess_thenExpectValidVehicle() {
        Optional<VehicleEntity> vehicle = getVehicleUseCase.process(1L);
        assertTrue(vehicle.isPresent());
        assertEquals(1L, vehicle.get().getId());
        assertEquals("VALID", vehicle.get().getStatus());
    }

    @Test
    void givenValidId_whenGetVehicleUseCaseProcess_thenExpectExpiredVehicle() {
        Optional<VehicleEntity> vehicle = getVehicleUseCase.process(2L);
        assertTrue(vehicle.isPresent());
        assertEquals(2L, vehicle.get().getId());
        assertEquals("EXPIRED", vehicle.get().getStatus());
    }

    @Test
    void givenValidId_whenGetVehicleUseCaseProcess_thenExpectNotFound() {
        Optional<VehicleEntity> vehicle = getVehicleUseCase.process(3L);
        assertTrue(vehicle.isEmpty());
    }
}