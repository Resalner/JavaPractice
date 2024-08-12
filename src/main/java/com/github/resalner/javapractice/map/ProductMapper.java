package com.github.resalner.javapractice.map;

import com.github.resalner.javapractice.model.Product;
import com.github.resalner.javapractice.dto.ProductResponse;
import com.github.resalner.javapractice.request.ProductRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {

	@Mapping(source = "id", target = "id")
	@Mapping(source = "name", target = "name")
	@Mapping(source = "description", target = "description")
	@Mapping(source = "price", target = "price")
	ProductResponse toResponse(Product product);

	@Mapping(source = "name", target = "name")
	@Mapping(source = "description", target = "description")
	@Mapping(source = "price", target = "price")
	@Mapping(source = "categoryId", target = "category.id")
	Product toProduct(ProductRequest productRequest);

	List<ProductResponse> toDomain(List<Product> product);
}