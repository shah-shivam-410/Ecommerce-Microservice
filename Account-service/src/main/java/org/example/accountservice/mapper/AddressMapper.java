package org.example.accountservice.mapper;

import org.example.accountservice.dto.AddressDto;
import org.example.accountservice.entity.Address;

public final class AddressMapper {
    private AddressMapper() {}

    public static Address toEntity(AddressDto dto) {
        if (dto == null) return null;
        return Address.builder()
                .id(dto.getId())
                .street(dto.getStreet())
                .city(dto.getCity())
                .state(dto.getState())
                .country(dto.getCountry())
                .postalCode(dto.getPostalCode())
                .build();
    }

    public static AddressDto toDto(Address entity) {
        if (entity == null) return null;
        return AddressDto.builder()
                .id(entity.getId())
                .street(entity.getStreet())
                .city(entity.getCity())
                .state(entity.getState())
                .country(entity.getCountry())
                .postalCode(entity.getPostalCode())
                .build();
    }
}

