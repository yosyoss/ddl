package com.ddl.service;

import com.ddl.model.response.CategoryResponse;

import java.util.List;

public interface CategoryService {
    CategoryResponse getCategory(String type);

    List<CategoryResponse> getAllCategories();
}
