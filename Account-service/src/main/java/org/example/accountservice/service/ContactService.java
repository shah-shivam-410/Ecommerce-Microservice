package org.example.accountservice.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.example.accountservice.dto.ContactDto;
import org.example.accountservice.entity.Account;
import org.example.accountservice.entity.Contact;
import org.example.accountservice.mapper.ContactMapper;
import org.example.accountservice.repository.AccountRepository;
import org.example.accountservice.repository.ContactRepository;
import org.springframework.stereotype.Service;

@Service
public class ContactService {

    private final ContactRepository contactRepository;
    private final AccountRepository accountRepository;

    public ContactService(ContactRepository contactRepository, AccountRepository accountRepository) {
        this.contactRepository = contactRepository;
        this.accountRepository = accountRepository;
    }

    public ContactDto create(ContactDto dto) {
        Contact contact = ContactMapper.toEntity(dto);
        if (dto.getAccountId() != null) {
            Account account = accountRepository.findById(dto.getAccountId()).orElse(null);
            contact.setAccount(account);
        }
        Contact saved = contactRepository.save(contact);
        return ContactMapper.toDto(saved);
    }

    public Optional<ContactDto> getById(Long id) {
        return contactRepository.findById(id).map(ContactMapper::toDto);
    }

    public List<ContactDto> list() {
        return contactRepository.findAll().stream().map(ContactMapper::toDto).collect(Collectors.toList());
    }

    public Optional<ContactDto> update(Long id, ContactDto dto) {
        return contactRepository.findById(id).map(existing -> {
            existing.setName(dto.getName());
            existing.setEmail(dto.getEmail());
            existing.setPhone(dto.getPhone());
            if (dto.getAccountId() != null) {
                Account account = accountRepository.findById(dto.getAccountId()).orElse(null);
                existing.setAccount(account);
            }
            Contact saved = contactRepository.save(existing);
            return ContactMapper.toDto(saved);
        });
    }

    public boolean delete(Long id) {
        if (!contactRepository.existsById(id)) return false;
        contactRepository.deleteById(id);
        return true;
    }

    public List<ContactDto> findByAccountId(Long accountId) {
        return contactRepository.findByAccountId(accountId).stream().map(ContactMapper::toDto).collect(Collectors.toList());
    }
}

