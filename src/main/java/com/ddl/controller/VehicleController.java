package com.ddl.controller;

import com.ddl.entity.Vehicle;
import com.ddl.model.response.CommonResponse;
import com.ddl.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vehicles")
@RequiredArgsConstructor
public class VehicleController {
    private final VehicleService vehicleService;

    @PostMapping("/park")
    public ResponseEntity<Void> parkVehicle(@RequestBody Vehicle request) {
        vehicleService.parkVehicle(request.getId());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/leave")
    public ResponseEntity<Void> leaveParking(@RequestParam String vehicleId) {
        vehicleService.leaveParking(vehicleId);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<CommonResponse<?>> getAllVehicles(Pageable pageable) {
        CommonResponse<?> response = vehicleService.findAllVehicle(pageable);
        return ResponseEntity.ok(response);
    }
}
