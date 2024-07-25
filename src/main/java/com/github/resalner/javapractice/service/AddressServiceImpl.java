package com.github.resalner.javapractice.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.resalner.javapractice.exception.EntityNotFoundException;
import com.github.resalner.javapractice.model.Address;
import com.github.resalner.javapractice.repository.AddressRepository;
import com.github.resalner.javapractice.map.AddressMapper;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    @Override
    public List<Address> getAddresses() {
        return addressRepository.findAll();
    }

    @Override
    public Address saveAddress(Address address) {
        address = addressRepository.save(address);
        return address;
    }

    @Override
    public Address getAddress(long id) {
        return addressRepository.findById(id)
                .orElseThrow(() -> EntityNotFoundException("не найден адрес с id = " + id));
    }

    @Override
    public void deleteAddress(long id) {
        addressRepository.deleteById(id)
                .orElseThrow(() -> EntityNotFoundException("не найден адрес с id = " + id));
    }

    @Override
    public Address updateAddress(long id, Address addressForUpdate) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> EntityNotFoundException("не найден адрес с id = " + id));
        if (Objects.nonNull(addressForUpdate.city())
                && !"".equals(addressForUpdate.city())) {

            address.setCity(addressForUpdate.city());
        }
        if (Objects.nonNull(addressForUpdate.street())
                && !"".equals(addressForUpdate.street())) {

            address.setStreet(addressForUpdate.street());
        }
        if (Objects.nonNull(addressForUpdate.houseNumber())
                && !"".equals(addressForUpdate.houseNumber())) {

            address.setHouseNumber(addressForUpdate.houseNumber());
        }
        if (Objects.nonNull(addressForUpdate.apartamentNumber())
                && !"".equals(addressForUpdate.apartamentNumber())) {

            address.setApartamentNumber(addressForUpdate.apartamentNumber());
        }
        address = addressRepository.save(address);
        return address;
    }
}