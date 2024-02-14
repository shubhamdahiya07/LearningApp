package com.effigo.assignment.learningapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.effigo.assignment.learningapp.models.Address;

public interface AddressRepo extends JpaRepository<Address, Integer> {

}
