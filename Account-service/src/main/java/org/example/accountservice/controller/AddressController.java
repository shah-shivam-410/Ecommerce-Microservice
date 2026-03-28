package org.example.accountservice.controller;

import java.net.URI;
import java.util.List;

import org.example.accountservice.dto.AddressDto;
import org.example.accountservice.service.AddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/address")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping
    public ResponseEntity<AddressDto> create(@Valid @RequestBody AddressDto dto) {
        AddressDto created = addressService.create(dto);
        return ResponseEntity.created(URI.create("/addresses/" + created.getId())).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressDto> getById(@PathVariable Long id) {
        return addressService.getById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<AddressDto>> list() {
        return ResponseEntity.ok(addressService.list());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressDto> update(@PathVariable Long id, @Valid @RequestBody AddressDto dto) {
        return addressService.update(id, dto).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean deleted = addressService.delete(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}

