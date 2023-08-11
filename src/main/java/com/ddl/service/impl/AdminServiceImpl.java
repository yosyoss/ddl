package com.ddl.service.impl;

import com.ddl.entity.Admin;
import com.ddl.model.request.AdminRequest;
import com.ddl.model.response.AdminResponse;
import com.ddl.model.response.CommonResponse;
import com.ddl.repository.AdminRepository;
import com.ddl.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;

    @Override
    public Admin create(AdminRequest request) {
        try {
            Admin admin = Admin.builder()
                    .username(request.getUsername())
                    .password(request.getPassword())
                    .build();
            return adminRepository.saveAndFlush(admin);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Error while creating admin");
        }
    }

    @Override
    public Admin getById(String id) {
        Optional<Admin> adminOptional = adminRepository.findById(id);
        if (adminOptional.isPresent()) {
            return adminOptional.get();
        } else {
            throw new IllegalArgumentException("Admin not found with ID: " + id);
        }
    }

    @Override
    public CommonResponse<List<AdminResponse>> findAllAdmin(Pageable pageable) {
        try {
            Page<Admin> adminPage = adminRepository.findAll(pageable);
            List<AdminResponse> collect = adminPage.getContent().stream()
                    .map(this::convertToAdminResponse)
                    .collect(Collectors.toList());
            return CommonResponse.<List<AdminResponse>>builder()
                    .statusCode(HttpStatus.OK.value())
                    .message("Success found all admin")
                    .data(collect)
                    .build();
        } catch (Exception e) {
            throw new RuntimeException("Admin not found");
        }
    }

    @Override
    public Admin updateById(Admin request) {
        Optional<Admin> adminOptional = Optional.ofNullable(adminRepository
                .findById(request.getId()).orElseThrow(
                        () -> new RuntimeException("Admin not found")));
        if (adminOptional.isEmpty()) {
            throw new RuntimeException("Admin not found");
        }
        adminOptional.get().setName(request.getName());
        adminOptional.get().setPhoneNumber(request.getPhoneNumber());
        adminRepository.save(adminOptional.get());
        return adminOptional.get();
    }

    @Override
    public Admin deleteById(String adminDelete) {
        Admin admin = getById(adminDelete);
        adminRepository.delete(admin);
        return admin;
    }

    private AdminResponse convertToAdminResponse(Admin admin) {
        return AdminResponse.builder()
                .id(admin.getId())
                .username(admin.getUsername())
                .name(admin.getName())
                .build();
    }
}