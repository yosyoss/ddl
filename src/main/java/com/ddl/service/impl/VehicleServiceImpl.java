package com.ddl.service.impl;

import com.ddl.entity.Parking;
import com.ddl.entity.Vehicle;
import com.ddl.model.response.CommonResponse;
import com.ddl.model.response.PagingResponse;
import com.ddl.model.response.VehicleResponse;
import com.ddl.repository.VehicleRepository;
import com.ddl.service.ParkingService;
import com.ddl.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {
    private final VehicleRepository vehicleRepository;
    private final ParkingService parkingService;

    @Override
    public Vehicle create(Vehicle vehicle) {
        try {
            Vehicle vehicle1 = Vehicle.builder()
                    .noPol(vehicle.getNoPol())
                    .category(vehicle.getCategory())
                    .parking(vehicle.getParking())
                    .build();
            return vehicleRepository.saveAndFlush(vehicle1);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Error while checking vehicle");
        }
    }

    @Override
    public Vehicle getById(String id) {
        return vehicleRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehicle not found"));
    }

    @Override
    public CommonResponse<List<VehicleResponse>> findAllVehicle(Pageable pageable) {
        Page<Vehicle> vehiclePage = vehicleRepository.findAll(pageable);
        List<VehicleResponse> vehicleResponses = vehiclePage.getContent().stream()
                .map(this::convertToVehicleResponse)
                .collect(Collectors.toList());

        return CommonResponse.<List<VehicleResponse>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Success found all vehicle")
                .data(vehicleResponses)
                .paging(PagingResponse.builder()
                        .currentPage(vehiclePage.getNumber())
                        .totalPage(vehiclePage.getTotalPages())
                        .size(vehiclePage.getSize())
                        .build())
                .build();
    }

    @Override
    public Vehicle updateById(String id, Vehicle vehicleUpdate) {
        Vehicle vehicle = getById(id);
        vehicle.setNoPol(vehicleUpdate.getNoPol());
        vehicle.setCategory(vehicleUpdate.getCategory());
        return vehicleRepository.save(vehicle);
    }

    @Override
    public Vehicle deleteById(String id) {
        Vehicle vehicle = getById(id);
        vehicleRepository.delete(vehicle);
        return vehicle;
    }

    @Override
    public void parkVehicle(String vehicleId) {
        Vehicle vehicle = getById(vehicleId);

        if (vehicle.getParking() == null) {
            Parking parking = new Parking();
            parking.setVehicle(vehicle);
            parking.setEntryTime(LocalDateTime.now());

            parkingService.create(parking);
            vehicle.setParking(parking);
            vehicleRepository.save(vehicle);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Vehicle already parked");
        }
    }

    @Override
    public void leaveParking(String vehicleId) {
        Vehicle vehicle = getById(vehicleId);

        if (vehicle.getParking() != null) {
            Parking parking = vehicle.getParking();
            parking.setExitTime(LocalDateTime.now());
            Duration parkingDuration = Duration.between(parking.getEntryTime(), parking.getExitTime());
            parking.setParkingDuration(parkingDuration);

            parkingService.update(parking);
            vehicle.setParking(null);
            vehicleRepository.save(vehicle);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Vehicle not parked");
        }
    }

    private VehicleResponse convertToVehicleResponse(Vehicle vehicle) {
        return VehicleResponse.builder()
                .id(vehicle.getId())
                .noPol(vehicle.getNoPol())
                .category(vehicle.getCategory().getName())
                .build();
    }
}
