package com.ddl.service;

import com.ddl.entity.Parking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ParkingService {
    Parking create(Parking parking);

    Parking getById(String id);

    Page<Parking> getAllParkings(Pageable pageable);

    Parking update(Parking parking);
}
