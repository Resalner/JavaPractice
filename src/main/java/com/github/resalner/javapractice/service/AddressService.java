package com.github.resalner.javapractice.service;

import com.github.resalner.javapractice.model.Address;

import java.util.List;

public interface AddressService {
    List<Address> getAddresses();

    Address getAddress(long id);

    Address saveAddress(Address address);

    Address updateAddress(long id, Address address);

    void deleteAddress(long id);

}