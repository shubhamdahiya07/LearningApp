package com.effigo.assignment.learningapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.effigo.assignment.learningapp.models.Address;
import com.effigo.assignment.learningapp.repositories.AddressRepo;

@Service
public class AddressService {

	@Autowired
	private AddressRepo addressRepo;

	public Address createAddress(Address address) {
		this.addressRepo.save(address);
		return address;
	}
}
