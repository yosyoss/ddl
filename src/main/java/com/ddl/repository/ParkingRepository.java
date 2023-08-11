package com.ddl.repository;

import com.ddl.entity.Parking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingRepository extends JpaRepository<Parking, String> {
}
