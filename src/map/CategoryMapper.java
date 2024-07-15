package com.github.resalner.javapractice.map;

import com.github.resalner.javapractice.model.Category;
import com.github.resalner.javapractice.response.CategoryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    @Mapping(target = "name", source = "name")
    Category toCategory(CategoryResponse categoryResponse);
}