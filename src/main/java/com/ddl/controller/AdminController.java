package com.ddl.controller;

import com.ddl.entity.Admin;
import com.ddl.model.request.AdminRequest;
import com.ddl.model.response.CommonResponse;
import com.ddl.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @PostMapping("/register")
    public ResponseEntity<?> registerAdmin(@RequestBody AdminRequest adminRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.<Admin>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Success create new admin")
                        .data(adminService.create(adminRequest))
                        .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAdminById(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK.value())
                .body(CommonResponse.<Admin>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Success get admin by id")
                        .data(adminService.getById(id))
                        .build());
    }

    @GetMapping
    public ResponseEntity<?> getAllAdmin(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.status(HttpStatus.OK.value())
                .body(adminService.findAllAdmin(pageable).getData());
    }

    @PutMapping
    public ResponseEntity<?> updateAdmin(@RequestBody Admin adminRequest) {
        Admin admin = adminService.updateById(adminRequest);
        return ResponseEntity.status(HttpStatus.OK.value())
                .body(CommonResponse.<Admin>builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Success update")
                        .data(admin)
                        .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAdmin(@PathVariable String id) {
        Admin admin = adminService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Success delete admin")
                        .data(admin)
                        .build());
    }
}
