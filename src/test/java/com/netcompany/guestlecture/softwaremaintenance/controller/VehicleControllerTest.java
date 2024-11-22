package com.netcompany.guestlecture.softwaremaintenance.controller;

import com.netcompany.guestlecture.softwaremaintenance.domain.VehicleEntity;
import com.netcompany.guestlecture.softwaremaintenance.usecase.GetVehicleUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
class VehicleControllerTest {
    @Mock
    GetVehicleUseCase getVehicleUseCase;
    @InjectMocks
    private VehicleController vehicleController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        Mockito.doReturn(Optional.of(new VehicleEntity(1L, "VALID"))).when(getVehicleUseCase).process(1L);
        Mockito.doReturn(Optional.of(new VehicleEntity(2L, "EXPIRED"))).when(getVehicleUseCase).process(2L);
        Mockito.doReturn(Optional.empty()).when(getVehicleUseCase).process(3L);
    }

    @Test
    void givenValidId_WhenGetVehicleStatus_ThenExpectStatusRegisteret() {
        ResponseEntity<String> vehicleStatus = vehicleController.getVehicleStatus(1L);
        assertTrue(vehicleStatus.getStatusCode().is2xxSuccessful());
        assertEquals("Vehicle is registered.", vehicleStatus.getBody());
    }

    @Test
    void givenValidId_WhenGetVehicleStatus_ThenExpectStatusNotRegisteret() {
        ResponseEntity<String> vehicleStatus = vehicleController.getVehicleStatus(2L);
        assertTrue(vehicleStatus.getStatusCode().is2xxSuccessful());
        assertEquals("Vehicle is not registered.", vehicleStatus.getBody());
    }

    @Test
    void givenValidId_WhenGetVehicleStatus_ThenExpectStatusNotFound() {
        ResponseEntity<String> vehicleStatus = vehicleController.getVehicleStatus(3L);
        assertTrue(vehicleStatus.getStatusCode().is4xxClientError());
        assertEquals("Vehicle not found with ID: 3", vehicleStatus.getBody());
    }
}