package org.example.accountservice.controller;

import java.net.URI;
import java.util.List;

import org.example.accountservice.dto.AccountDto;
import org.example.accountservice.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }
 
    @PostMapping
    public ResponseEntity<AccountDto> create(@Valid @RequestBody AccountDto dto) {
        AccountDto created = accountService.create(dto);
        return ResponseEntity.created(URI.create("/accounts/" + created.getAccountId())).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getById(@PathVariable Long id) {
        return accountService.getById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<AccountDto>> list() {
        return ResponseEntity.ok(accountService.list());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean deleted = accountService.delete(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}

