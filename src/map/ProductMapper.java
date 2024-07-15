package com.github.resalner.javapractice.map;

import com.github.resalner.javapractice.model.Product;
import com.github.resalner.javapractice.response.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(target = "name", source = "name")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "categoryId", source = "categoryId")
    Product toProduct(ProductResponse productResponse);
}