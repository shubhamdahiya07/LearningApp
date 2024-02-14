package com.effigo.assignment.learningapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.effigo.assignment.learningapp.models.Address;
import com.effigo.assignment.learningapp.services.AddressService;

@RestController
public class AddressController {
	@Autowired
	private AddressService addressService;

	public AddressController(AddressService addressService) {
		super();
		this.addressService = addressService;
	}

	@PostMapping("/address")
	public ResponseEntity<Address> createAddress(@RequestBody Address address) {
		Address createdAddress = addressService.createAddress(address);
		return new ResponseEntity<Address>(createdAddress, HttpStatus.CREATED);
	}

}
