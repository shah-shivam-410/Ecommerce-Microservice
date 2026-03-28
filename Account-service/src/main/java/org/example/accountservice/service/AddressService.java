package org.example.accountservice.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.example.accountservice.dto.AddressDto;
import org.example.accountservice.entity.Address;
import org.example.accountservice.mapper.AddressMapper;
import org.example.accountservice.repository.AddressRepository;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public AddressDto create(AddressDto dto) {
        Address saved = addressRepository.save(AddressMapper.toEntity(dto));
        return AddressMapper.toDto(saved);
    }

    public Optional<AddressDto> getById(Long id) {
        return addressRepository.findById(id).map(AddressMapper::toDto);
    }

    public List<AddressDto> list() {
        return addressRepository.findAll().stream().map(AddressMapper::toDto).collect(Collectors.toList());
    }

    public Optional<AddressDto> update(Long id, AddressDto dto) {
        return addressRepository.findById(id).map(existing -> {
            existing.setStreet(dto.getStreet());
            existing.setCity(dto.getCity());
            existing.setState(dto.getState());
            existing.setCountry(dto.getCountry());
            existing.setPostalCode(dto.getPostalCode());
            Address saved = addressRepository.save(existing);
            return AddressMapper.toDto(saved);
        });
    }

    public boolean delete(Long id) {
        if (!addressRepository.existsById(id)) return false;
        addressRepository.deleteById(id);
        return true;
    }
}

