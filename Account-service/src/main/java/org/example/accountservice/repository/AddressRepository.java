package org.example.accountservice.repository;

import java.util.List;

import org.example.accountservice.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findByCountry(String country);
}

