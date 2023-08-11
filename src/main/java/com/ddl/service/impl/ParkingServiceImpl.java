package com.ddl.service.impl;

import com.ddl.entity.Parking;
import com.ddl.repository.ParkingRepository;
import com.ddl.service.ParkingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class ParkingServiceImpl implements ParkingService {
    private final ParkingRepository parkingRepository;

    @Override
    public Parking create(Parking parking) {
        try {
            return parkingRepository.saveAndFlush(parking);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Error while creating parking");
        }
    }
    @Override
    public Parking getById(String id) {
        return parkingRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Parking not found"));
    }

    @Override
    public Page<Parking> getAllParkings(Pageable pageable) {
        return parkingRepository.findAll(pageable);
    }

    @Override
    public Parking update(Parking parking) {
        return parkingRepository.save(parking);
    }
}
