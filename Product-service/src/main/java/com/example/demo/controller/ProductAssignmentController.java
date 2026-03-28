package com.example.demo.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ProductAssignmentDto;
import com.example.demo.service.ProductAssignmentService;

@RestController
@RequestMapping("/product/assignments")
public class ProductAssignmentController {

    private final ProductAssignmentService productAssignmentService;

    public ProductAssignmentController(ProductAssignmentService productAssignmentService) {
        this.productAssignmentService = productAssignmentService;
    }

    @PostMapping
    public ResponseEntity<ProductAssignmentDto> assign(@RequestBody ProductAssignmentDto dto) {
        ProductAssignmentDto created = productAssignmentService.assign(dto);
        return ResponseEntity.created(URI.create("/account-product/" + created.getId())).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductAssignmentDto> getById(@PathVariable Long id) {
        return productAssignmentService.getById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<ProductAssignmentDto>> list() {
        return ResponseEntity.ok(productAssignmentService.listAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean deleted = productAssignmentService.delete(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
