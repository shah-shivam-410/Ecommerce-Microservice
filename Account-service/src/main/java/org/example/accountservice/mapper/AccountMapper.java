package org.example.accountservice.mapper;

import java.util.stream.Collectors;

import org.example.accountservice.dto.AccountDto;
import org.example.accountservice.entity.Account;

public class AccountMapper {

    private AccountMapper() {}

    public static Account toEntity(AccountDto dto) {
        if (dto == null) return null;

        Account.AccountBuilder builder = Account.builder()
                .id(dto.getAccountId())
                .name(dto.getAccountName())
                .type(dto.getAccountType());
        if (dto.getAddress() != null) {
            builder.address(AddressMapper.toEntity(dto.getAddress()));
        }
         if (dto.getContacts() != null) {
             builder.contacts(dto.getContacts().stream()
                 .map(ContactMapper::toEntity)
                 .collect(Collectors.toList()));
         }

        return builder.build();
    }

    public static AccountDto toDto(Account entity) {
        if (entity == null) return null;

        AccountDto.AccountDtoBuilder builder = AccountDto.builder()
                .accountId(entity.getId())
                .accountName(entity.getName())
                .accountType(entity.getType());
        if(entity.getAddress() != null) {
            builder.address(AddressMapper.toDto(entity.getAddress()));
        }
         if (entity.getContacts() != null) {
             builder.contacts(entity.getContacts().stream()
                 .map(ContactMapper::toDto)
                 .collect(Collectors.toList()));
         }

        return builder.build();
    }

}
