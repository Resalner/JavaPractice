package com.github.resalner.javapractice.controller;

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
import com.github.resalner.javapractice.model.Address;
import com.github.resalner.javapractice.repository.AddressRepository;
import com.github.resalner.javapractice.request.AddressRequest;
import com.github.resalner.javapractice.service.AddressService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/addresses")
@RequiredArgsConstructor
public class AddressController {

    @Autowired
    private final AddressService addressService;

    @GetMapping
    public List<Address> getAddresses() {
        return addressService.getAddresses();
    }

    @PostMapping("/addAddress")
    public void saveAddress(@RequestBody @Valid AddressRequest addressRequest) {
        addressService.addAddress(addressRequest);
    }

    @GetMapping("/{id}")
    public Address getAddress(@PathVariable("id") long addressid) {
        return addressService.getAddress(addressid);
    }

    @DeleteMapping("/{id}")
    public void deleteAddress(@PathVariable("id") long addressid) {
        addressService.deleteAddress(addressid);
    }

    @PutMapping("/{id}")
    public Address updateAddress(@PathVariable("id") long addressid, @RequestBody @Valid AddressRequest addressRequest) {
        return addressService.updateAddress(addressid, addressRequest);
    }
}