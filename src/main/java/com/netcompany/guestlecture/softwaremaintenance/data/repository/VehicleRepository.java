package com.netcompany.guestlecture.softwaremaintenance.data.repository;

import com.netcompany.guestlecture.softwaremaintenance.data.jpa.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
}
