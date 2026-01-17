package org.example.accountservice.mapper;

import org.example.accountservice.dto.ContactDto;
import org.example.accountservice.entity.Contact;

public final class ContactMapper {
    private ContactMapper() {}

    public static Contact toEntity(ContactDto dto) {
        if (dto == null) return null;
        return Contact.builder()
                .id(dto.getId())
                .name(dto.getName())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .build();
    }

    public static ContactDto toDto(Contact entity) {
        if (entity == null) return null;
        Long accountId = entity.getAccount() != null ? entity.getAccount().getAccountId() : null;
        return ContactDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .phone(entity.getPhone())
                .accountId(accountId)
                .build();
    }
}

