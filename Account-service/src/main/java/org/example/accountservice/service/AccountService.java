package org.example.accountservice.service;

import org.example.accountservice.dto.AccountDto;
import org.example.accountservice.dto.AddressDto;
import org.example.accountservice.entity.Account;
import org.example.accountservice.entity.Address;
import org.example.accountservice.entity.Contact;
import org.example.accountservice.repository.AccountRepository;
import org.example.accountservice.repository.AddressRepository;
import org.example.accountservice.mapper.AccountMapper;
import org.example.accountservice.mapper.AddressMapper;
import org.example.accountservice.mapper.ContactMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountService {

    AccountRepository accountRepository;
    AddressRepository addressRepository;

    public AccountService(AccountRepository accountRepository, AddressRepository addressRepository) {
        this.accountRepository = accountRepository;
        this.addressRepository = addressRepository;
    }

    @Transactional
    public AccountDto create(AccountDto dto) {
        Account account = AccountMapper.toEntity(dto);
        // persist address first if present to ensure managed relationship
        Address addr = account.getAddress();
        if (addr != null) {
            Address saved = addressRepository.save(addr);
            account.setAddress(saved);
        }
        // ensure contacts point back to account
        if (account.getContacts() != null) {
            account.getContacts().forEach(c -> c.setAccount(account));
        }
        Account saved = accountRepository.save(account);
        return AccountMapper.toDto(saved);
    }

    public Optional<AccountDto> getById(Long id) {
        return accountRepository.findById(id).map(AccountMapper::toDto);
    }

    public List<AccountDto> list() {
        return accountRepository.findAll().stream().map(AccountMapper::toDto).collect(Collectors.toList());
    }

    @Transactional
    public Optional<AccountDto> update(Long id, AccountDto dto) {
        return accountRepository.findById(id).map(existing -> {
            existing.setAccountName(dto.getAccountName());
            existing.setAccountType(dto.getAccountType());
            // handle address
            AddressDto addressDto = dto.getAddress();
            if (addressDto != null) {
                Address addressEntity = AddressMapper.toEntity(addressDto);
                Address savedAddress = addressRepository.save(addressEntity);
                existing.setAddress(savedAddress);
            } else {
                existing.setAddress(null);
            }
            // handle contacts: replace existing contacts with new ones
            if (dto.getContacts() != null) {
                List<Contact> newContacts = dto.getContacts().stream().map(ContactMapper::toEntity).collect(Collectors.toList());
                newContacts.forEach(c -> c.setAccount(existing));
                if (existing.getContacts() == null) {
                    existing.setContacts(newContacts);
                } else {
                    existing.getContacts().clear();
                    existing.getContacts().addAll(newContacts);
                }
            } else {
                if (existing.getContacts() != null) {
                    existing.getContacts().clear();
                }
            }
            Account saved = accountRepository.save(existing);
            return AccountMapper.toDto(saved);
        });
    }

    public boolean delete(Long id) {
        if (!accountRepository.existsById(id)) return false;
        accountRepository.deleteById(id);
        return true;
    }


}
