package org.example.accountservice.controller;

import org.example.accountservice.dto.ContactDto;
import org.example.accountservice.service.ContactService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/contact")
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping
    public ResponseEntity<ContactDto> create(@Valid @RequestBody ContactDto dto) {
        ContactDto created = contactService.create(dto);
        return ResponseEntity.created(URI.create("/contacts/" + created.getId())).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactDto> getById(@PathVariable Long id) {
        return contactService.getById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<ContactDto>> list() {
        return ResponseEntity.ok(contactService.list());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContactDto> update(@PathVariable Long id, @Valid @RequestBody ContactDto dto) {
        return contactService.update(id, dto).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean deleted = contactService.delete(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/by-account/{accountId}")
    public ResponseEntity<List<ContactDto>> byAccount(@PathVariable Long accountId) {
        return ResponseEntity.ok(contactService.findByAccountId(accountId));
    }
}

