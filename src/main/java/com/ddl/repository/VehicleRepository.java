package com.ddl.repository;

import com.ddl.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, String> {
}
