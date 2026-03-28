package org.example.accountservice.repository;

import java.util.List;

import org.example.accountservice.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    List<Contact> findByAccountId(Long accountId);
}

