package com.company.productservice.service.implementation;

import com.company.productservice.dto.category.CategoryResponse;
import com.company.productservice.entity.CategoryEntity;
import com.company.productservice.mapper.CategoryMapper;
import com.company.productservice.repository.CategoryRepository;
import com.company.productservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImplementation implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryResponse> getAllCategories() {
        List<CategoryEntity> categories = categoryRepository.findAll();
        return categories
                .stream()
                .map(CategoryMapper::mapToCategoryResponse)
                .collect(Collectors.toList());
    }
}
