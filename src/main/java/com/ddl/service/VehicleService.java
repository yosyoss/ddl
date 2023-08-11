package com.ddl.service;

import com.ddl.entity.Vehicle;
import com.ddl.model.response.CommonResponse;
import com.ddl.model.response.VehicleResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface VehicleService {
    Vehicle create(Vehicle customer);

    Vehicle getById(String id);


    CommonResponse<List<VehicleResponse>> findAllVehicle(Pageable pageable);

    Vehicle updateById(String id, Vehicle customerUpdate);

    Vehicle deleteById(String id);
    void parkVehicle(String id);
    void leaveParking(String id);
}
