package com.ddl.service;

import com.ddl.entity.Admin;
import com.ddl.model.request.AdminRequest;
import com.ddl.model.response.AdminResponse;
import com.ddl.model.response.CommonResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdminService {

    Admin create(AdminRequest request);

    Admin getById(String id);
    CommonResponse<List<AdminResponse>> findAllAdmin(Pageable pageable);
    Admin deleteById(String id);
    Admin updateById(Admin request);
}

