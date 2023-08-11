package com.ddl.service.impl;

import com.ddl.entity.Category;
import com.ddl.model.response.CategoryResponse;
import com.ddl.repository.CategoryRepository;
import com.ddl.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public CategoryResponse getCategory(String type) {
        Optional<Object> categoryOptional = categoryRepository.findByNameIgnoreCase(type);

        if (categoryOptional.isPresent()) {
            Category category = (Category) categoryOptional.get();
            return CategoryResponse.builder()
                    .id(category.getId())
                    .name(category.getName())
                    .build();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found");
        }
    }


    @Override
    public List<CategoryResponse> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(category -> CategoryResponse.builder()
                        .id(category.getId())
                        .name(category.getName())
                        .build())
                .collect(Collectors.toList());
    }
}