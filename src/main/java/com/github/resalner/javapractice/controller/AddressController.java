package com.github.resalner.javapractice.controller;

import com.github.resalner.javapractice.model.Address;
import com.github.resalner.javapractice.repository.AddressRepository;
import com.github.resalner.javapractice.request.AddressRequest;
import com.github.resalner.javapractice.service.AddressService;
import com.github.resalner.javapractice.dto.AddressResponse;
import com.github.resalner.javapractice.map.AddressMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/addresses")
@RequiredArgsConstructor
public class AddressController {

    @Autowired
    private final AddressMapper mapper;
    private final AddressService addressService;

    @GetMapping
    public List<AddressResponse> getAddresses() {
        List<Address> addresses = addressService.getAddresses();
        return mapper.toDomain(addresses);
    }

    @PostMapping
    public AddressResponse saveAddress(@RequestBody @Valid AddressRequest addressRequest) {
        Address address = mapper.toAddress(addressRequest);
        addressService.saveAddress(address);
        return mapper.toResponse(address);
    }

    @GetMapping("/{id}")
    public AddressResponse getAddress(@PathVariable("id") long addressid) {
        Address address = addressService.getAddress(addressid);
        return mapper.toResponse(address);
    }

    @DeleteMapping("/{id}")
    public void deleteAddress(@PathVariable("id") long addressid) {
        addressService.deleteAddress(addressid);
    }

    @PutMapping("/{id}")
    public AddressResponse updateAddress(@PathVariable("id") long addressid, @RequestBody @Valid AddressRequest addressRequest) {
        Address address = mapper.toAddress(addressRequest);
        address = addressService.updateAddress(addressid, address);
        return mapper.toResponse(address);
    }
}