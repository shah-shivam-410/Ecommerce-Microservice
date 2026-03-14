package org.example.accountservice.repository;

import org.example.accountservice.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    List<Contact> findByAccountId(Long accountId);
}

