package com.github.resalner.javapractice.map;

import com.github.resalner.javapractice.model.Category;
import com.github.resalner.javapractice.dto.CategoryResponse;
import com.github.resalner.javapractice.request.CategoryRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoryMapper {

    CategoryResponse toResponse(Category category);

    Category toCategory(CategoryRequest categoryRequest);

    public List<CategoryResponse> toDomain(List<Category> categories);
}