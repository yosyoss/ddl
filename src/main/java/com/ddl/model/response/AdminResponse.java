package com.ddl.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class AdminResponse {
    private String id;
    private String username;
    private String name;
    private String phoneNumber;
}
