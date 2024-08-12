package com.github.resalner.javapractice.map;

import com.github.resalner.javapractice.model.Address;
import com.github.resalner.javapractice.dto.AddressResponse;
import com.github.resalner.javapractice.request.AddressRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AddressMapper {

    AddressResponse toResponse(Address address);

    Address toAddress(AddressRequest addressRequest);

    List<AddressResponse> toDomain(List<Address> address);

}