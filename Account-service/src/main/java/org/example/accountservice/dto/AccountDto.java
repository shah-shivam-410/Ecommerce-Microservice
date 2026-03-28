package org.example.accountservice.dto;

import java.util.List;

import org.example.accountservice.constants.AccountType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountDto {
    private Long accountId;
    private String accountName;
    private AccountType accountType;
    private AddressDto address;
    private List<ContactDto> contacts;
}

