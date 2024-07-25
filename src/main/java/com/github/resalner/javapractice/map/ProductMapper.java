package com.github.resalner.javapractice.map;

import com.github.resalner.javapractice.model.Product;
import com.github.resalner.javapractice.dto.ProductResponse;
import com.github.resalner.javapractice.request.ProductRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {

    ProductResponse toResponse(Product product);

    List<ProductResponse> toDomain(List<Product> product);

    Product toProduct(ProductRequest productRequest);
}