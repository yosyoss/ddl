package com.ddl.controller;

import com.ddl.entity.Parking;
import com.ddl.model.response.CommonResponse;
import com.ddl.model.response.PagingResponse;
import com.ddl.service.ParkingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/parkings")
@RequiredArgsConstructor
public class ParkingController {

    private final ParkingService parkingService;

    @PostMapping
    public ResponseEntity<Parking> createParking(@RequestBody Parking parking) {
        Parking createdParking = parkingService.create(parking);
        return ResponseEntity.ok(createdParking);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Parking> getParkingById(@PathVariable String id) {
        Parking parking = parkingService.getById(id);
        return ResponseEntity.ok(parking);
    }

    @GetMapping
    public ResponseEntity<CommonResponse<Page<Parking>>> getAllParkings(Pageable pageable) {
        Page<Parking> parkings = parkingService.getAllParkings(pageable);
        PagingResponse pagingResponse = PagingResponse.builder()
                .currentPage(parkings.getNumber())
                .totalPage(parkings.getTotalPages())
                .size(parkings.getSize())
                .build();
        CommonResponse<Page<Parking>> response = CommonResponse.<Page<Parking>>builder()
                .statusCode(200)
                .message("Success")
                .data(parkings)
                .paging(pagingResponse)
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Parking> updateParking(@PathVariable String id, @RequestBody Parking parkingUpdate) {
        Parking updatedParking = parkingService.update(parkingUpdate);
        return ResponseEntity.ok(updatedParking);
    }
}
