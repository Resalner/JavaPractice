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
				.orElseThrow(() -> new EntityNotFoundException("не найден адрес с id = " + id));
	}

	@Override
	public void deleteAddress(long id) {
		addressRepository.deleteById(id);
	}

	@Override
	public Address updateAddress(long id, Address addressForUpdate) {
		Address address = addressRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("не найден адрес с id = " + id));
		String newCity = addressForUpdate.getCity();
		String newStreet = addressForUpdate.getStreet();
		String newHouseNumber = addressForUpdate.getHouseNumber();
		String newApartmentNumber = addressForUpdate.getApartmentNumber();

		if (Objects.nonNull(newCity) && !"".equals(newCity)) {

			address.setCity(newCity);
		}
		if (Objects.nonNull(newStreet) && !"".equals(newStreet)) {

			address.setStreet(newStreet);
		}
		if (Objects.nonNull(newHouseNumber) && !"".equals(newHouseNumber)) {

			address.setHouseNumber(newHouseNumber);
		}
		if (Objects.nonNull(newApartmentNumber) && !"".equals(newApartmentNumber)) {

			address.setApartmentNumber(newApartmentNumber);
		}
		address = addressRepository.save(address);
		return address;
	}
}