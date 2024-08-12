package com.github.resalner.javapractice.service;

import org.springframework.stereotype.Service;
import com.github.resalner.javapractice.exception.EntityNotFoundException;
import com.github.resalner.javapractice.model.Address;
import com.github.resalner.javapractice.repository.AddressRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

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
		return getAddressIfExists(id);
	}

	@Override
	public void deleteAddress(long id) {
		if (!addressRepository.existsById(id)) {
			throw new EntityNotFoundException("Не найден продукт с id = " + id);
		}
		addressRepository.deleteById(id);
	}

	@Override
	public Address updateAddress(long id, Address addressForUpdate) {
		Address address = getAddressIfExists(id);
		String newCity = addressForUpdate.getCity();
		String newStreet = addressForUpdate.getStreet();
		String newHouseNumber = addressForUpdate.getHouseNumber();
		String newApartmentNumber = addressForUpdate.getApartmentNumber();

		address.setCity(newCity);
		address.setStreet(newStreet);
		address.setHouseNumber(newHouseNumber);
		address.setApartmentNumber(newApartmentNumber);
		
		address = addressRepository.save(address);
		
		return address;
	}
	
	private Address getAddressIfExists(long id) {
		return addressRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("не найден адрес с id = " + id));
	}
}