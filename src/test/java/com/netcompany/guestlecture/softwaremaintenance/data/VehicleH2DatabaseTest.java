package com.netcompany.guestlecture.softwaremaintenance.data;

import com.netcompany.guestlecture.softwaremaintenance.data.jpa.Vehicle;
import com.netcompany.guestlecture.softwaremaintenance.data.repository.VehicleRepository;
import com.netcompany.guestlecture.softwaremaintenance.domain.VehicleEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class VehicleH2DatabaseTest {
    @Mock
    VehicleRepository vehicleRepository;

    @InjectMocks
    VehicleH2Database vehicleH2Database;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        Mockito.doReturn(Optional.of(getMockedVehicleJpa(1L, "VALID"))).when(vehicleRepository).findById(1L);
        Mockito.doReturn(Optional.of(getMockedVehicleJpa(2L, "EXPIRED"))).when(vehicleRepository).findById(2L);
        Mockito.doReturn(Optional.empty()).when(vehicleRepository).findById(3L);
    }

    private Vehicle getMockedVehicleJpa(long id, String status) {
        Vehicle vehicle = new Vehicle();
        vehicle.setId(id);
        vehicle.setRegistrationStatus(status);

        return vehicle;
    }

    @Test
    void givenValidId_WhenGetVehicleEntity_ThenExpectStatusValid() {
        Optional<VehicleEntity> vehicleEntity = vehicleH2Database.getVehicleEntity(1L);
        assertTrue(vehicleEntity.isPresent());
        assertEquals(1L, vehicleEntity.get().getId());
        assertEquals("VALID", vehicleEntity.get().getStatus());
    }

    @Test
    void givenValidId_WhenGetVehicleEntity_ThenExpectStatusExpired() {
        Optional<VehicleEntity> vehicleEntity = vehicleH2Database.getVehicleEntity(2L);
        assertTrue(vehicleEntity.isPresent());
        assertEquals(2L, vehicleEntity.get().getId());
        assertEquals("EXPIRED", vehicleEntity.get().getStatus());
    }

    @Test
    void givenValidId_WhenGetVehicleEntity_ThenExpectNotFound() {
        Optional<VehicleEntity> vehicleEntity = vehicleH2Database.getVehicleEntity(3L);
        assertTrue(vehicleEntity.isEmpty());
    }
}