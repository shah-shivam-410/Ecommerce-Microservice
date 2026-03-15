package org.example.accountservice.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.example.accountservice.dto.AccountDto;
import org.example.accountservice.entity.Account;
import org.example.accountservice.entity.Address;
import org.example.accountservice.mapper.AccountMapper;
import org.example.accountservice.repository.AccountRepository;
import org.example.accountservice.repository.AddressRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public boolean delete(Long id) {
        if (!accountRepository.existsById(id)) return false;
        accountRepository.deleteById(id);
        return true;
    }


}
