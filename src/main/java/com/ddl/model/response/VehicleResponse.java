package com.ddl.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class VehicleResponse {
    private String id;
    private String noPol;
    private String category;
}