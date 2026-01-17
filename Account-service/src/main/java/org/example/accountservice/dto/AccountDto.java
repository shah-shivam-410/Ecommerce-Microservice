package org.example.accountservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.example.accountservice.constants.AccountType;

import java.util.List;

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

